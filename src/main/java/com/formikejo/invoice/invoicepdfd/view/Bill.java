package com.formikejo.invoice.invoicepdfd.view;

import java.math.BigDecimal;
import java.util.List;


public class Bill {
    private List<InvoiceLine> invoiceLines;
    private BigDecimal subTotal ;
    private BigDecimal tax ;
    private String taxType;
    private BigDecimal taxPercent;
    private BigDecimal total;

    public Bill(List<InvoiceLine> invoiceLines, BigDecimal subTotal, BigDecimal tax,String taxType,BigDecimal taxPercent, BigDecimal total) {
        this.invoiceLines = invoiceLines;
        this.total = total;
        this.subTotal = subTotal;
        this.tax = tax;
        this.taxType = taxType;
        this.taxPercent = taxPercent;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public String getTaxType() {
        return taxType;
    }
}
