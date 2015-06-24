package com.formikejo.invoice.invoicepdfd.view;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
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
		assertThat(view.getSender().getCompanyName(), is("Consortial"));
		// todo: check other sender fields
	}

	@Test
	public void testReceiver() {
		assertThat(view.getReceiver().getCompanyName(), is("IYT Corporation"));
		// todo: check other receiver fields
	}


}
