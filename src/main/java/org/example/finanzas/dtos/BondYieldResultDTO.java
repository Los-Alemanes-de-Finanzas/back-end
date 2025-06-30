package org.example.finanzas.dtos;

public class BondYieldResultDTO {
    private int bondId;
    private double tcea;
    private double trea;
    private double macaulayDuration;
    private double modifiedDuration;
    private double convexity;
    private double currentPrice;
    private double profitOrLoss;
    private double maxMarketPrice; // ✅ NUEVO

    // Getters & Setters

    public int getBondId() {
        return bondId;
    }

    public void setBondId(int bondId) {
        this.bondId = bondId;
    }

    public double getTcea() {
        return tcea;
    }

    public void setTcea(double tcea) {
        this.tcea = tcea;
    }

    public double getTrea() {
        return trea;
    }

    public void setTrea(double trea) {
        this.trea = trea;
    }

    public double getMacaulayDuration() {
        return macaulayDuration;
    }

    public void setMacaulayDuration(double macaulayDuration) {
        this.macaulayDuration = macaulayDuration;
    }

    public double getModifiedDuration() {
        return modifiedDuration;
    }

    public void setModifiedDuration(double modifiedDuration) {
        this.modifiedDuration = modifiedDuration;
    }

    public double getConvexity() {
        return convexity;
    }

    public void setConvexity(double convexity) {
        this.convexity = convexity;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public double getMaxMarketPrice() { // ✅ NUEVO
        return maxMarketPrice;
    }

    public void setMaxMarketPrice(double maxMarketPrice) { // ✅ NUEVO
        this.maxMarketPrice = maxMarketPrice;
    }
}

