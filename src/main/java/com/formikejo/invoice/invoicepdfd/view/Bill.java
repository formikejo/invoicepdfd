package com.formikejo.invoice.invoicepdfd.view;

import java.math.BigDecimal;
import java.util.List;


public class Bill {
    private List<InvoiceLine> invoiceLines;
    private BigDecimal totalAmount;

    public Bill(List<InvoiceLine> invoiceLines, BigDecimal totalAmount) {
        this.invoiceLines = invoiceLines;
        this.totalAmount = totalAmount;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
