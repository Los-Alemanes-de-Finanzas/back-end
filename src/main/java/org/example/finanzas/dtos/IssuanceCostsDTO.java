package org.example.finanzas.dtos;

public class IssuanceCostsDTO {

    private int id;
    private int bondId;
    private float premium;
    private float structuringCost;
    private String whoPaysStructuring;
    private float placementCost;
    private String whoPaysPlacement;
    private float flotationCost;
    private String whoPaysFloatation;
    private float cavaliFee;
    private String whoPaysCavali;
    private float initialCostIssuer;
    private float initialCostBondholder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBondId() {
        return bondId;
    }

    public void setBondId(int bondId) {
        this.bondId = bondId;
    }

    public float getPremium() {
        return premium;
    }

    public void setPremium(float premium) {
        this.premium = premium;
    }

    public float getStructuringCost() {
        return structuringCost;
    }

    public void setStructuringCost(float structuringCost) {
        this.structuringCost = structuringCost;
    }

    public String getWhoPaysStructuring() {
        return whoPaysStructuring;
    }

    public void setWhoPaysStructuring(String whoPaysStructuring) {
        this.whoPaysStructuring = whoPaysStructuring;
    }

    public float getPlacementCost() {
        return placementCost;
    }

    public void setPlacementCost(float placementCost) {
        this.placementCost = placementCost;
    }

    public String getWhoPaysPlacement() {
        return whoPaysPlacement;
    }

    public void setWhoPaysPlacement(String whoPaysPlacement) {
        this.whoPaysPlacement = whoPaysPlacement;
    }

    public float getFlotationCost() {
        return flotationCost;
    }

    public void setFlotationCost(float flotationCost) {
        this.flotationCost = flotationCost;
    }

    public String getWhoPaysFloatation() {
        return whoPaysFloatation;
    }

    public void setWhoPaysFloatation(String whoPaysFloatation) {
        this.whoPaysFloatation = whoPaysFloatation;
    }

    public float getCavaliFee() {
        return cavaliFee;
    }

    public void setCavaliFee(float cavaliFee) {
        this.cavaliFee = cavaliFee;
    }

    public String getWhoPaysCavali() {
        return whoPaysCavali;
    }

    public void setWhoPaysCavali(String whoPaysCavali) {
        this.whoPaysCavali = whoPaysCavali;
    }

    public float getInitialCostIssuer() {
        return initialCostIssuer;
    }

    public void setInitialCostIssuer(float initialCostIssuer) {
        this.initialCostIssuer = initialCostIssuer;
    }

    public float getInitialCostBondholder() {
        return initialCostBondholder;
    }

    public void setInitialCostBondholder(float initialCostBondholder) {
        this.initialCostBondholder = initialCostBondholder;
    }
}