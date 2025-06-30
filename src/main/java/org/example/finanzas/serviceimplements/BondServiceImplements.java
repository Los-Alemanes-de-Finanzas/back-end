package org.example.finanzas.serviceimplements;

import jakarta.transaction.Transactional;
import org.example.finanzas.dtos.FinalCostsResultDTO;
import org.example.finanzas.entities.Bond;
import org.example.finanzas.entities.IssuanceCosts;
import org.example.finanzas.repositories.IBondRepository;
import org.example.finanzas.repositories.IIssuanceCostsRepository;
import org.example.finanzas.serviceinterfaces.IBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
