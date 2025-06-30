package org.example.finanzas.dtos;

public class FinalCostsResultDTO {

    private Integer bondId;
    private Float nominalValue;
    private Float commercialValue;
    private Float totalInitialCostIssuer;
    private Float totalInitialCostBondholder;
    private Float netAmountReceivedByIssuer; // Cuánto recibe realmente el emisor
    private Float netAmountPaidByBondholder; // Cuánto paga realmente el bonista

    // Constructores, Getters y Setters

    public FinalCostsResultDTO() {
    }

    // Getters y Setters para todos los campos...

    public Integer getBondId() {
        return bondId;
    }

    public void setBondId(Integer bondId) {
        this.bondId = bondId;
    }

    public Float getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(Float nominalValue) {
        this.nominalValue = nominalValue;
    }

    public Float getCommercialValue() {
        return commercialValue;
    }

    public void setCommercialValue(Float commercialValue) {
        this.commercialValue = commercialValue;
    }

    public Float getTotalInitialCostIssuer() {
        return totalInitialCostIssuer;
    }

    public void setTotalInitialCostIssuer(Float totalInitialCostIssuer) {
        this.totalInitialCostIssuer = totalInitialCostIssuer;
    }

    public Float getTotalInitialCostBondholder() {
        return totalInitialCostBondholder;
    }

    public void setTotalInitialCostBondholder(Float totalInitialCostBondholder) {
        this.totalInitialCostBondholder = totalInitialCostBondholder;
    }

    public Float getNetAmountReceivedByIssuer() {
        return netAmountReceivedByIssuer;
    }

    public void setNetAmountReceivedByIssuer(Float netAmountReceivedByIssuer) {
        this.netAmountReceivedByIssuer = netAmountReceivedByIssuer;
    }

    public Float getNetAmountPaidByBondholder() {
        return netAmountPaidByBondholder;
    }

    public void setNetAmountPaidByBondholder(Float netAmountPaidByBondholder) {
        this.netAmountPaidByBondholder = netAmountPaidByBondholder;
    }
}
