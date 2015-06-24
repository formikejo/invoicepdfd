package com.formikejo.invoice.invoicepdfd.view;

import io.dropwizard.views.View;

public class InvoiceView extends View {

    private Receiver receiver;
    private Sender sender;
    private Bill bill;
    private String issueDate;
    private String id;
    private String paymentTerms;

    public InvoiceView(Receiver nReceiver, Sender nSender, Bill nBill, String nIssueDate, String nid, String nPaymentTerms) {
        super("report.ftl");
        receiver = nReceiver;
        sender = nSender;
        bill = nBill;
        issueDate = nIssueDate;
        id = nid;
        paymentTerms = nPaymentTerms;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public Bill getBill() {
        return bill;
    }

    public Sender getSender() {
        return sender;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getId() {
        return id;
    }
}