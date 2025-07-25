package org.example.finanzas.dtos;

import java.time.LocalDate;

public class BondDTO {

    private int id;
    private String name;
    private int userId;
    private Float nominalValue;
    private Float commercialValue;
    private Integer numberOfYears;
    private String couponFrequency;
    private Integer daysPerYear;
    private String interestRateType;
    private Integer capitalization;
    private Float interestRate;
    private Float annualDiscountRate;
    private LocalDate issueDate;
    private Integer gracePeriodType;
    private Integer gracePeriodDuration;
    private String currency;
    private Float effectiveAnnualRate;
    private Float cokPerPeriod;

    // Getters y Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public String getCouponFrequency() {
        return couponFrequency;
    }

    public void setCouponFrequency(String couponFrequency) {
        this.couponFrequency = couponFrequency;
    }

    public Integer getDaysPerYear() {
        return daysPerYear;
    }

    public void setDaysPerYear(Integer daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    public String getInterestRateType() {
        return interestRateType;
    }

    public void setInterestRateType(String interestRateType) {
        this.interestRateType = interestRateType;
    }

    public Integer getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(Integer capitalization) {
        this.capitalization = capitalization;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public Float getAnnualDiscountRate() {
        return annualDiscountRate;
    }

    public void setAnnualDiscountRate(Float annualDiscountRate) {
        this.annualDiscountRate = annualDiscountRate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getGracePeriodType() {
        return gracePeriodType;
    }

    public void setGracePeriodType(Integer gracePeriodType) {
        this.gracePeriodType = gracePeriodType;
    }

    public Integer getGracePeriodDuration() {
        return gracePeriodDuration;
    }

    public void setGracePeriodDuration(Integer gracePeriodDuration) {
        this.gracePeriodDuration = gracePeriodDuration;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getEffectiveAnnualRate() {
        return effectiveAnnualRate;
    }

    public void setEffectiveAnnualRate(Float effectiveAnnualRate) {
        this.effectiveAnnualRate = effectiveAnnualRate;
    }

    public Float getCokPerPeriod() {
        return cokPerPeriod;
    }

    public void setCokPerPeriod(Float cokPerPeriod) {
        this.cokPerPeriod = cokPerPeriod;
    }

}