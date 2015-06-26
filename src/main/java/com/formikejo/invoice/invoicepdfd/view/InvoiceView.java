package com.formikejo.invoice.invoicepdfd.view;

import io.dropwizard.views.View;

public class InvoiceView extends View {

    private Receiver receiver;
    private Sender sender;
    private Bill bill;
    private String issueDate;
    private String id;
    private String paymentTerms;
    private String imgStream;
    private String layoutScheme;

    public InvoiceView(Receiver nReceiver, Sender nSender, Bill nBill, String nIssueDate, String nid, String nPaymentTerms, String nImgStream, String nLayoutScheme) {
        super("report.ftl");
        receiver = nReceiver;
        sender = nSender;
        bill = nBill;
        issueDate = nIssueDate;
        id = nid;
        paymentTerms = nPaymentTerms;
        imgStream = nImgStream;
        if (nLayoutScheme.equals("Style 1")) {
            layoutScheme = "/assets/Graphics Scheme 1.css";
        }
        if (nLayoutScheme.equals("Style 2")) {
            layoutScheme = "/assets/Graphics Scheme 2.css";
        }

    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getImgStream() {
        return imgStream;
    }

    public String getLayoutScheme() {
        return layoutScheme;
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