package com.formikejo.invoice.invoicepdfd.view;

import java.math.BigDecimal;

public class InvoiceLine {

    private String description;
    private BigDecimal amount;
    private BigDecimal rate;
    private BigDecimal total;

    public InvoiceLine(String nDescription, BigDecimal nAmount, BigDecimal nRate) {
        description = nDescription;
        amount = nAmount;
        rate = nRate;
        total = amount.multiply(rate);
    }

    public InvoiceLine(String nDescription, BigDecimal nAmount, BigDecimal nRate, BigDecimal nTotal) {

        description = nDescription;
        amount = nAmount;
        rate = nRate;
        total = nTotal;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
