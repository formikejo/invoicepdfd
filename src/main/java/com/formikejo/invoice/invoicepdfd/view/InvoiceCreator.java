package com.formikejo.invoice.invoicepdfd.view;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class InvoiceCreator {

    private Document d;

    public InvoiceCreator(Document d) {
        this.d = d;
    }

    public InvoiceView getDataFromXML() throws XPathExpressionException {

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = (NodeList) xpath.evaluate("/Invoice/InvoiceLine", d, XPathConstants.NODESET);

        List<InvoiceLine> lines = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node invoiceLine = nodes.item(i);
            String description = xpath.evaluate("Item//Name", invoiceLine);
            BigDecimal rate = new BigDecimal(xpath.evaluate("//Price//PriceAmount", invoiceLine));
            BigDecimal amount = new BigDecimal(xpath.evaluate("InvoicedQuantity", invoiceLine));
            BigDecimal value = new BigDecimal(xpath.evaluate("LineExtensionAmount", invoiceLine));

            lines.add(new InvoiceLine(description, amount, rate, value));
        }

        BigDecimal subTotal = new BigDecimal(xpathEval("//LegalMonetaryTotal/TaxExclusiveAmount"));
        BigDecimal tax = new BigDecimal(xpathEval("//TaxTotal/TaxAmount"));
        BigDecimal total = new BigDecimal(xpathEval("//LegalMonetaryTotal/PayableAmount"));
        String taxType = (xpathEval("//TaxTotal//TaxScheme//TaxTypeCode"));
        BigDecimal taxPercent = new BigDecimal(xpathEval("//TaxTotal//TaxCategory//Percent"));

        Bill bill = new Bill(lines, subTotal, tax, taxType, taxPercent, total);

        Receiver receiver = new Receiver(
                xpathEval("//AccountingCustomerParty//Contact//Name"),
                xpathEval("//AccountingCustomerParty//PostalAddress//StreetName") + " " + xpathEval("//AccountingCustomerParty//PostalAddress//BuildingNumber") + ", " + xpathEval("//AccountingCustomerParty//PostalAddress//BuildingName"),
                xpathEval("//AccountingCustomerParty//PostalAddress//PostalZone") + " " + xpathEval("//AccountingCustomerParty//PostalAddress//CityName") + ", " + xpathEval("//AccountingCustomerParty//PostalAddress//CountrySubentity"),
                xpathEval("//AccountingCustomerParty//PartyName//Name"),
                xpathEval("/Invoice/OrderReference//ID"));

        Sender sender = new Sender(
                xpathEval("//AccountingSupplierParty//PostalAddress//StreetName") + " " + xpathEval("//AccountingSupplierParty//PostalAddress//BuildingNumber") + ", " + xpathEval("//AccountingSupplierParty//PostalAddress//BuildingName"),
                xpathEval("//AccountingSupplierParty//PostalAddress//PostalZone") + " " + xpathEval("//AccountingSupplierParty//PostalAddress//CityName") + ", " + xpathEval("//AccountingSupplierParty//PostalAddress//CountrySubentity"),
                xpathEval("//AccountingSupplierParty//Contact//ElectronicMail"),
                xpathEval("//AccountingSupplierParty//Contact//Telephone"),
                xpathEval("//AccountingSupplierParty//PartyName//Name"),
                xpathEval("//AccountingSupplierParty/Party/PartyIdentification/ID"),
                xpathEval("//PaymentMeans/PayeeFinancialAccount/ID"),
                xpathEval("//Party/PartyTaxScheme/CompanyID"));

        String issueDate = xpathEval("/Invoice/IssueDate");
        String invoiceID = xpathEval("/Invoice/ID");
        String paymentTerms = xpathEval("/Invoice/PaymentTerms/Note");

        return new InvoiceView(receiver, sender, bill, issueDate, invoiceID, paymentTerms);
    }

    private String xpathEval(String path) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        return xpath.evaluate(path, d);
    }
}
