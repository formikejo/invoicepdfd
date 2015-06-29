package com.formikejo.invoice.invoicepdfd;

import com.formikejo.invoice.invoicepdfd.view.InvoiceCreator;
import com.formikejo.invoice.invoicepdfd.view.InvoiceView;
import org.eclipse.jetty.http.HttpStatus;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class InvoiceService {

	private InvoiceViewRepository repository;
	private String addressOfThisServer;

	public InvoiceService(InvoiceViewRepository repository, String addressOfThisServer) {
		this.repository = repository;
		this.addressOfThisServer = addressOfThisServer;
	}

	public URI getInvoiceURI(InputStream inputStream, String imgStream, String layoutScheme) throws ParserConfigurationException, XPathExpressionException {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
		} catch (SAXException | IOException e) {
			throw new WebApplicationException("No XML provided", HttpStatus.BAD_REQUEST_400);
		}
		InvoiceView invV = new InvoiceCreator(document, imgStream, layoutScheme).getDataFromXML();

		return UriBuilder.fromUri(addressOfThisServer + "/invoice/" + repository.store(invV)).build();
	}
}
