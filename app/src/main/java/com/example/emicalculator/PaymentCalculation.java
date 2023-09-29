package com.example.emicalculator;

import java.io.Serializable;

public class PaymentCalculation implements Serializable {

    private double principalPayment;
    private double interestRate;
    private double amortizationPeriod;

    public PaymentCalculation() {

        principalPayment = 0;
        interestRate = 0;
        amortizationPeriod = 0;

    }

    public void setValues(String principalPayment, String interestRate, String amortizationPeriod) {
        this.principalPayment = Double.parseDouble(principalPayment);
        this.interestRate = Double.parseDouble(interestRate);
        this.amortizationPeriod = Double.parseDouble(amortizationPeriod);
    }

    public String[] getValues() {

        return new String[]{String.valueOf(principalPayment), String.valueOf(interestRate),
                String.valueOf(amortizationPeriod)};
    }

    public double calculatePayment() {
        double monthlyRate = interestRate / 12 / 100;
        double adjustedAmortization = amortizationPeriod * 12;
        double interestFormatter = Math.pow(1 + (monthlyRate), adjustedAmortization);
        return (principalPayment * monthlyRate * interestFormatter) / (interestFormatter - 1);
    }

}
