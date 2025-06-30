package org.example.finanzas.dtos;

public class IssuanceCostsCreateDTO {
    private int id;
    private int bondId;

    private Float premium;
    private Float structuringCost;
    private String whoPaysStructuring;
    private Float placementCost;
    private String whoPaysPlacement;
    private Float flotationCost;
    private String whoPaysFloatation;
    private Float cavaliFee;
    private String whoPaysCavali;

    // --- GETTERS Y SETTERS ---

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

    public Float getPremium() {
        return premium;
    }

    public void setPremium(Float premium) {
        this.premium = premium;
    }

    public Float getStructuringCost() {
        return structuringCost;
    }

    public void setStructuringCost(Float structuringCost) {
        this.structuringCost = structuringCost;
    }

    public String getWhoPaysStructuring() {
        return whoPaysStructuring;
    }

    public void setWhoPaysStructuring(String whoPaysStructuring) {
        this.whoPaysStructuring = whoPaysStructuring;
    }

    public Float getPlacementCost() {
        return placementCost;
    }

    public void setPlacementCost(Float placementCost) {
        this.placementCost = placementCost;
    }

    public String getWhoPaysPlacement() {
        return whoPaysPlacement;
    }

    public void setWhoPaysPlacement(String whoPaysPlacement) {
        this.whoPaysPlacement = whoPaysPlacement;
    }

    public Float getFlotationCost() {
        return flotationCost;
    }

    public void setFlotationCost(Float flotationCost) {
        this.flotationCost = flotationCost;
    }

    public String getWhoPaysFloatation() {
        return whoPaysFloatation;
    }

    public void setWhoPaysFloatation(String whoPaysFloatation) {
        this.whoPaysFloatation = whoPaysFloatation;
    }

    public Float getCavaliFee() {
        return cavaliFee;
    }

    public void setCavaliFee(Float cavaliFee) {
        this.cavaliFee = cavaliFee;
    }

    public String getWhoPaysCavali() {
        return whoPaysCavali;
    }

    public void setWhoPaysCavali(String whoPaysCavali) {
        this.whoPaysCavali = whoPaysCavali;
    }
}
