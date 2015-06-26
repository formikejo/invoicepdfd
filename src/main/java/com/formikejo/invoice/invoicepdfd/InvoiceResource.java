package com.formikejo.invoice.invoicepdfd;

import com.codahale.metrics.annotation.Timed;
import com.formikejo.invoice.invoicepdfd.view.InvoiceCreator;
import com.formikejo.invoice.invoicepdfd.view.InvoiceView;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

@Path("/")
public class InvoiceResource {

	InvoiceViewRepository repository = new InvoiceViewRepository();

	@Timed
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/invoice")
	public Response processInvoice(
			@FormDataParam("xml") final InputStream inputStream,
			@FormDataParam("xml") final FormDataContentDisposition contentDispositionHeader,
			@FormDataParam("img") final String imgStream,
			@FormDataParam("layoutScheme") final String layoutScheme
	) throws ParserConfigurationException, XPathExpressionException {
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
		} catch (SAXException | IOException e) {
			throw new WebApplicationException("No XML provided", HttpStatus.BAD_REQUEST_400);
		}
		InvoiceView invV = new InvoiceCreator(document, imgStream, layoutScheme).getDataFromXML();

		URI uri = UriBuilder.fromUri("/invoice/" + repository.store(invV)).build();
		return Response.seeOther(uri).build();
	}

	@Timed
	@GET
	@Produces("text/html")
	@Path("/invoice/{UUID}")
	public InvoiceView getInvoice(@PathParam("UUID") UUID uuid) {
		return repository.get(uuid);
	}


}

