package org.example.finanzas.serviceinterfaces;

import org.example.finanzas.dtos.FinalCostsResultDTO;
import org.example.finanzas.entities.Bond;
import org.example.finanzas.entities.IssuanceCosts;

import java.util.List;

public interface IBondService {
    public void insert(Bond device);
    public List<Bond> list();
    public void delete(int id);
    public Bond listId(int id);
    public void update(Bond device);

    // --- NUEVOS MÉTODOS DE CÁLCULO ---
    /**
     * Calcula valores fundamentales del bono como la TEA y la tasa efectiva del período (COK del período).
     * Guarda estos resultados en la entidad Bond.
     * @param bondId El ID del bono a calcular.
     * @return El bono actualizado con los cálculos iniciales.
     */
    Bond calculateInitialValues(Integer bondId);

    /**
     * Calcula los costos de emisión y las métricas finales (flujos netos) del bono.
     * @param bondId El ID del bono a calcular.
     * @return Un objeto con las métricas finales (ej. TCEA, TREA, flujos).
     * (Nota: Crearemos un DTO para esto)
     */
    // Por ahora, empecemos con calcular y guardar los costos.
    FinalCostsResultDTO calculateFinalCosts(Integer bondId);
}
