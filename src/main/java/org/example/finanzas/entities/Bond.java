package org.example.finanzas.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bond")
public class Bond {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "nominal_value", nullable = false)
    private Float nominalValue;

    @Column(name = "comercial_bond", nullable = false)
    private Float commercialValue;

    @Column(name = "n_years", nullable = false)
    private Integer numberOfYears;

    @Column(name = "coupon_frequency", nullable = false, length = 50)
    private String couponFrequency;

    @Column(name = "days_per_year", nullable = false)
    private Integer daysPerYear;

    @Column(name = "type_interest_rate", nullable = false, length = 50)
    private String interestRateType;

    @Column(name = "capitalization", nullable = false)
    private Integer capitalization;

    @Column(name = "interest_rate", nullable = false)
    private Float interestRate;

    @Column(name = "annual_discount_rate", nullable = false)
    private Float annualDiscountRate;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    // El tipo de dato en la DB es int, se mantiene como Integer.
    @Column(name = "type_grace_period", nullable = false)
    private Integer gracePeriodType;

    @Column(name = "grace_period_duration", nullable = false)
    private Integer gracePeriodDuration;

    @Column(name = "money", nullable = false, length = 150)
    private String currency;

    @Column(name = "effective_annual_rate", nullable = false)
    private Float effectiveAnnualRate;

    @Column(name = "cok_per_period", nullable = false)
    private Float cokPerPeriod;

    public Bond() {
    }

    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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