package com.formikejo.invoice.invoicepdfd.view;

import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class InvoiceCreator {

    private Document d;

    public InvoiceCreator(Document d) {
        this.d = d;
    }

    public InvoiceView getDataFromXML() throws XPathExpressionException {

        InvoiceLine line1 = new InvoiceLine("Stuff", BigDecimal.valueOf(14), BigDecimal.valueOf(75), BigDecimal.valueOf(5000000));
        InvoiceLine line2 = new InvoiceLine("More stuff", BigDecimal.valueOf(34), BigDecimal.valueOf(34), BigDecimal.valueOf(34000));
        List<InvoiceLine> lines = Arrays.asList(line1, line2);


        BigDecimal subTotal = new BigDecimal(xpathEval("//LegalMonetaryTotal/LineExtensionAmount"));
        BigDecimal tax = new BigDecimal(xpathEval("//TaxTotal/TaxAmount"));
        BigDecimal total = new BigDecimal(xpathEval("//LegalMonetaryTotal/PayableAmount"));
        String taxType = (xpathEval("//TaxTotal//TaxScheme//TaxTypeCode"));
        BigDecimal taxPercent = new BigDecimal(xpathEval("//TaxTotal//TaxCategory//Percent"));

        Bill bill1 = new Bill(lines, subTotal,tax,taxType,taxPercent,total);

        Receiver receiver = new Receiver(
                xpathEval("//AccountingCustomerParty//Contact//Name"),
                xpathEval("//AccountingCustomerParty//PostalAddress//StreetName") + " " + xpathEval("//AccountingCustomerParty//PostalAddress//BuildingNumber") + ", " + xpathEval("//AccountingCustomerParty//PostalAddress//BuildingName"),
                xpathEval("//AccountingCustomerParty//PostalAddress//PostalZone") + " " + xpathEval("//AccountingCustomerParty//PostalAddress//CityName") + ", " + xpathEval("//AccountingCustomerParty//PostalAddress//CountrySubentity"),
                xpathEval("//AccountingCustomerParty//PartyName//Name"),
                xpathEval("/Invoice/OrderReference//ID"));

        Sender sender = new Sender(
                xpathEval("//AccountingSupplierParty//Contact//Name"),
                xpathEval("//AccountingSupplierParty//PostalAddress//StreetName") + " " + xpathEval("//AccountingSupplierParty//PostalAddress//BuildingNumber") + ", " + xpathEval("//AccountingSupplierParty//PostalAddress//BuildingName"),
                xpathEval("//AccountingSupplierParty//PostalAddress//PostalZone") + " " + xpathEval("//AccountingSupplierParty//PostalAddress//CityName") + ", " + xpathEval("//AccountingSupplierParty//PostalAddress//CountrySubentity"),
                xpathEval("//AccountingSupplierParty//Contact//ElectronicMail"),
                xpathEval("//AccountingSupplierParty//Contact//Telephone"),
                xpathEval("//AccountingSupplierParty//PartyName//Name"),
                xpathEval("//AccountingSupplierParty/Party/PartyIdentification/ID"),
                xpathEval("//PaymentMeans/PayeeFinancialAccount/ID"),
                xpathEval("//Party/PartyTaxScheme/CompanyID"));

        String IssueDate = xpathEval("/Invoice/IssueDate");

        String InvoiceID = xpathEval("/Invoice/ID");

        return new InvoiceView(receiver, sender, bill1,IssueDate , InvoiceID);
    }

    private String xpathEval(String path) throws XPathExpressionException {
        XPath xpath = XPathFactory.newInstance().newXPath();
        return xpath.evaluate(path, d);
    }
}
