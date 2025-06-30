package org.example.finanzas.dtos;

import java.time.LocalDate;

public class PaymentDTO {
    private int period;
    private LocalDate paymentDate;
    private float interest;
    private float amortization;
    private float cuota;
    private float saldo;

    public PaymentDTO() {}

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getAmortization() {
        return amortization;
    }

    public void setAmortization(float amortization) {
        this.amortization = amortization;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
