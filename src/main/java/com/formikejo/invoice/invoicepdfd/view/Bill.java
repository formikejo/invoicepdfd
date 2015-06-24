package com.formikejo.invoice.invoicepdfd.view;

import java.util.List;


public class Bill {
    private List<InvoiceLine> invoiceLines;

    public Bill(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }
}
