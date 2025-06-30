package org.example.finanzas.serviceimplements;

import jakarta.transaction.Transactional;
import org.example.finanzas.dtos.BondYieldResultDTO;
import org.example.finanzas.dtos.FinalCostsResultDTO;
import org.example.finanzas.dtos.PaymentDTO;
import org.example.finanzas.entities.Bond;
import org.example.finanzas.entities.IssuanceCosts;
import org.example.finanzas.repositories.IBondRepository;
import org.example.finanzas.repositories.IIssuanceCostsRepository;
import org.example.finanzas.serviceinterfaces.IBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class BondServiceImplements implements IBondService {
    @Autowired
    private IBondRepository bR;
    @Autowired
    private IIssuanceCostsRepository iR;

    @Override
    @Transactional
    public Bond calculateInitialValues(Integer bondId) {
        Bond bond = bR.findById(bondId)
                .orElseThrow(() -> new RuntimeException("Bono no encontrado con ID: " + bondId));

        // 1. Calcular la frecuencia de días por cupón
        int couponDays = switch (bond.getCouponFrequency().toLowerCase()) {
            case "mensual" -> 30;
            case "bimestral" -> 60;
            case "trimestral" -> 90;
            case "cuatrimestral" -> 120;
            case "semestral" -> 180;
            case "anual" -> 360;
            default -> throw new IllegalArgumentException("Frecuencia de cupón no válida: " + bond.getCouponFrequency());
        };

        // 2. Calcular Tasa Efectiva Anual (TEA) a partir de la Tasa de Interés del bono
        float tea;
        if ("nominal".equalsIgnoreCase(bond.getInterestRateType())) {
            float n = (float) bond.getDaysPerYear() / bond.getCapitalization();
            tea = (float) (Math.pow(1 + (bond.getInterestRate() / n), n) - 1);
        } else {
            tea = bond.getInterestRate(); // Ya es efectiva
        }
        bond.setEffectiveAnnualRate(tea);

        // 3. Calcular Tasa Efectiva del Período (COK del período)
        float cokPeriod = (float) (Math.pow(1 + bond.getAnnualDiscountRate(), (double) couponDays / bond.getDaysPerYear()) - 1);
        bond.setCokPerPeriod(cokPeriod);

        // 4. Guardar los resultados en la base de datos
        return bR.save(bond);
    }


    @Override
    @Transactional
    public FinalCostsResultDTO calculateFinalCosts(Integer bondId) { // ¡Cambiar el tipo de retorno!
        Bond bond = bR.findById(bondId)
                .orElseThrow(() -> new RuntimeException("Bono no encontrado con ID: " + bondId));

        IssuanceCosts costs = iR.findByBondId(bondId)
                .orElseThrow(() -> new RuntimeException("Costos de emisión no encontrados para el bono ID: " + bondId));

        float valorComercial = bond.getCommercialValue();

        // --- La lógica de cálculo de costos no cambia ---
        float costoInicialEmisor = 0f;
        // ... (cálculo de costoInicialEmisor) ...
        if ("emisor".equalsIgnoreCase(costs.getWhoPaysStructuring())) {
            costoInicialEmisor += costs.getStructuringCost() * valorComercial;
        }
        // ... etc ...
        costs.setInitialCostIssuer(costoInicialEmisor);

        float costoInicialBonista = 0f;
        // ... (cálculo de costoInicialBonista) ...
        costoInicialBonista += costs.getPremium() * valorComercial;
        if ("bonista".equalsIgnoreCase(costs.getWhoPaysStructuring())) {
            costoInicialBonista += costs.getStructuringCost() * valorComercial;
        }
        // ... etc ...
        costs.setInitialCostBondholder(costoInicialBonista);

        // Guardamos los costos calculados en la BD
        iR.save(costs);

        // --- ¡NUEVA LÓGICA! CONSTRUIR Y DEVOLVER EL DTO DE RESPUESTA ---
        FinalCostsResultDTO resultDTO = new FinalCostsResultDTO();
        resultDTO.setBondId(bond.getId());
        resultDTO.setNominalValue(bond.getNominalValue());
        resultDTO.setCommercialValue(bond.getCommercialValue());
        resultDTO.setTotalInitialCostIssuer(costs.getInitialCostIssuer());
        resultDTO.setTotalInitialCostBondholder(costs.getInitialCostBondholder());

        // Calcular y establecer los montos netos
        float netAmountIssuer = valorComercial - costs.getInitialCostIssuer();
        resultDTO.setNetAmountReceivedByIssuer(netAmountIssuer);

        float netAmountBondholder = valorComercial + costs.getInitialCostBondholder();
        resultDTO.setNetAmountPaidByBondholder(netAmountBondholder);

        return resultDTO; // Devolver el DTO de resultado
    }
    @Override
    public List<PaymentDTO> generatePaymentSchedule(Integer bondId) {
        Bond bond = bR.findById(bondId)
                .orElseThrow(() -> new RuntimeException("Bono no encontrado"));

        float saldo = bond.getNominalValue();
        int totalPeriods = bond.getNumberOfYears() * getPeriodsPerYear(bond.getCouponFrequency());
        float amortization = saldo / totalPeriods;
        float tasaPeriodo = bond.getCokPerPeriod();

        LocalDate fecha = bond.getIssueDate();
        int diasEntrePagos = getDaysByFrequency(bond.getCouponFrequency());

        List<PaymentDTO> pagos = new java.util.ArrayList<>();

        for (int i = 1; i <= totalPeriods; i++) {
            PaymentDTO p = new PaymentDTO();
            fecha = fecha.plusDays(diasEntrePagos);
            p.setPaymentDate(fecha);
            p.setPeriod(i);

            float interes = saldo * tasaPeriodo;
            float amort = amortization;
            float cuota = interes + amort;

            // Aplicar período de gracia
            if (i <= bond.getGracePeriodDuration()) {
                switch (bond.getGracePeriodType()) {
                    case 1: // Gracia parcial: solo intereses
                        amort = 0;
                        cuota = interes;
                        break;
                    case 2: // Gracia total: no se paga nada
                        interes = 0;
                        amort = 0;
                        cuota = 0;
                        break;
                    default:
                        break; // Gracia tipo 0: se paga normalmente
                }
            }

            p.setInterest(interes);
            p.setAmortization(amort);
            p.setCuota(cuota);

            saldo -= amort;
            p.setSaldo(Math.max(saldo, 0f)); // evitar negativos

            pagos.add(p);
        }

        return pagos;
    }


    private int getDaysByFrequency(String freq) {
        return switch (freq.toLowerCase()) {
            case "mensual" -> 30;
            case "bimestral" -> 60;
            case "trimestral" -> 90;
            case "cuatrimestral" -> 120;
            case "semestral" -> 180;
            case "anual" -> 360;
            default -> throw new IllegalArgumentException("Frecuencia no válida");
        };
    }

    private int getPeriodsPerYear(String freq) {
        return switch (freq.toLowerCase()) {
            case "mensual" -> 12;
            case "bimestral" -> 6;
            case "trimestral" -> 4;
            case "cuatrimestral" -> 3;
            case "semestral" -> 2;
            case "anual" -> 1;
            default -> throw new IllegalArgumentException("Frecuencia no válida");
        };
    }
    @Override
    @Transactional
    public BondYieldResultDTO calculateYields(Integer bondId) {
        Bond bond = bR.findById(bondId)
                .orElseThrow(() -> new RuntimeException("Bono no encontrado"));
        IssuanceCosts costs = iR.findByBondId(bondId)
                .orElseThrow(() -> new RuntimeException("Costos no encontrados"));

        float valorNominal = bond.getNominalValue();
        float valorComercial = bond.getCommercialValue();
        float tasa = bond.getEffectiveAnnualRate();
        int periodosTotales = bond.getNumberOfYears() * getPeriodosPorAnio(bond.getCouponFrequency());
        float saldo = valorNominal;
        float amortizacion = valorNominal / periodosTotales;
        float cok = bond.getCokPerPeriod();

        double valorActual = 0;
        double duracionMacaulay = 0;
        double convexidad = 0;

        for (int i = 1; i <= periodosTotales; i++) {
            float interes = saldo * tasa / getPeriodosPorAnio(bond.getCouponFrequency());
            float cuota = interes + amortizacion;
            double flujo = cuota;

            double descuento = Math.pow(1 + cok, i);
            valorActual += flujo / descuento;

            duracionMacaulay += (i * flujo) / descuento;
            convexidad += (flujo * i * (i + 1)) / Math.pow(1 + cok, i + 2);

            saldo -= amortizacion;
        }

        duracionMacaulay = duracionMacaulay / valorActual;
        double duracionModificada = duracionMacaulay / (1 + cok);
        double tcea = Math.pow(valorNominal / (valorComercial - costs.getInitialCostIssuer()), 1.0 / periodosTotales) - 1;
        double trea = Math.pow((valorComercial + costs.getInitialCostBondholder()) / valorNominal, 1.0 / periodosTotales) - 1;
        double utilidad = valorNominal - valorComercial;
        double precioActual = valorActual;

        // ✅ Calcular el Precio Máximo de Mercado (COK = 0)
        saldo = valorNominal;
        double maxMarketPrice = 0;
        for (int i = 1; i <= periodosTotales; i++) {
            float interes = saldo * tasa / getPeriodosPorAnio(bond.getCouponFrequency());
            float cuota = interes + amortizacion;
            maxMarketPrice += cuota;
            saldo -= amortizacion;
        }

        BondYieldResultDTO dto = new BondYieldResultDTO();
        dto.setBondId(bondId);
        dto.setTcea(tcea);
        dto.setTrea(trea);
        dto.setProfitOrLoss(utilidad);
        dto.setCurrentPrice(precioActual);
        dto.setMacaulayDuration(duracionMacaulay);
        dto.setModifiedDuration(duracionModificada);
        dto.setConvexity(convexidad);
        dto.setMaxMarketPrice(maxMarketPrice); // ✅ Nuevo campo

        return dto;
    }

    private int getPeriodosPorAnio(String frecuencia) {
        return switch (frecuencia.toLowerCase()) {
            case "mensual" -> 12;
            case "bimestral" -> 6;
            case "trimestral" -> 4;
            case "cuatrimestral" -> 3;
            case "semestral" -> 2;
            case "anual" -> 1;
            default -> throw new IllegalArgumentException("Frecuencia inválida");
        };
    }

    @Override
    public void insert(Bond device) {
       bR.save(device);
    }

    @Override
    public List<Bond> list() {
        return bR.findAll();
    }

    @Override
    public void delete(int id) {
        bR.deleteById(id);
    }

    @Override
    public Bond listId(int id) {
        return bR.findById(id).orElse(new Bond());
    }

    @Override
    public void update(Bond device) {
        bR.save(device);
    }
}
