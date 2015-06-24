package com.formikejo.invoice.invoicepdfd.view;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class InvoiceCreatorTest {

    InvoiceView view;

    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getClass().getResourceAsStream("/UBL-Invoice-2.0-Example.xml"));
        InvoiceCreator creator = new InvoiceCreator(d);
        view = creator.getDataFromXML();
    }

    @Test
    public void testSender() {
        assertThat(view.getSender().getAddress(), is("Busy Street 56A, Thereabouts"));
        assertThat(view.getSender().getvatNumber(), is("175 269 2355"));
        assertThat(view.getSender().getEmail(), is("bouquet@fpconsortial.co.uk"));
        assertThat(view.getSender().getTelephone(), is("0158 1233714"));
        assertThat(view.getSender().getPostalCode(), is("AA99 1BB Farthing, Heremouthshire"));
        assertThat(view.getSender().getBankAccount(), is("12345678"));
        assertThat(view.getSender().getCompanyName(), is("Consortial"));
        assertThat(view.getSender().getCompanyIdentification(), is("3759759732957"));
    }

    @Test
    public void testReceiver() {
        assertThat(view.getReceiver().getName(), is("Mr Fred Churchill"));
        assertThat(view.getReceiver().getOrderReferenceID(), is("AEG012345"));
        assertThat(view.getReceiver().getpostalCode(), is("ZZ99 1ZZ Bridgtow, Avon"));
        assertThat(view.getReceiver().getStreetName(), is("Avon Way 85B, House 2"));
        assertThat(view.getReceiver().getCompanyName(), is("IYT Corporation"));
    }

    @Test
    public void testBill() {
        assertThat(view.getBill().getSubTotal(), is(new BigDecimal("90.00")));
        assertThat(view.getBill().getTax(), is(new BigDecimal("17.50")));
        assertThat(view.getBill().getTotal(), is(new BigDecimal("107.50")));
        assertThat(view.getBill().getTaxPercent(), is(new BigDecimal("17.5")));
        assertThat(view.getBill().getTaxType(), is("VAT"));


        List<InvoiceLine> lines = view.getBill().getInvoiceLines();
        assertThat(lines, hasSize(1));

        assertThat(lines.get(0).getDescription(),is("beeswax"));
        assertThat(lines.get(0).getRate(),is(new BigDecimal("1.00")));
        assertThat(lines.get(0).getAmount(),is(new BigDecimal("90")));
        assertThat(lines.get(0).getTotal(),is(new BigDecimal("90.00")));

    }


}
