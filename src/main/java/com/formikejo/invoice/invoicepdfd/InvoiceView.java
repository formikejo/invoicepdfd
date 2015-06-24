package com.formikejo.invoice.invoicepdfd;

import io.dropwizard.views.View;

public class InvoiceView extends View {

    private Receiver receiver;
    private Sender sender;
    private Bill bill;
    private String issueDate;
    private String id;

    public InvoiceView(Receiver nReceiver, Sender nSender, Bill nBill, String nIssueDate, String nid) {
        super("report.ftl");
        receiver = nReceiver;
        sender = nSender;
        bill = nBill;
        issueDate = nIssueDate;
        id = nid;
    }

    public Receiver getReceiver() {
        return receiver;
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

    public String getid() {
        return id;
    }
}