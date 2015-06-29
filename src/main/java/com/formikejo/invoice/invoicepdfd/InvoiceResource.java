package com.formikejo.invoice.invoicepdfd;

import com.codahale.metrics.annotation.Timed;
import com.formikejo.invoice.invoicepdfd.view.InvoiceView;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.InputStream;
import java.net.URI;
import java.util.UUID;

@Path("/")
public class InvoiceResource {

	InvoiceViewRepository repository;
	InvoiceService service;

	public InvoiceResource(InvoiceViewRepository repository, String addressOfThisServer) {
		this.service = new InvoiceService(repository, addressOfThisServer);
		this.repository = repository;
	}

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
		URI uri = service.getInvoiceURI(inputStream, imgStream, layoutScheme);
		return Response.seeOther(uri).build();
	}

	@Timed
	@GET
	@Produces("text/html")
	@Path("/invoice/{UUID}")
	public InvoiceView getInvoice(@PathParam("UUID") UUID uuid) {
		return repository.get(uuid);
	}

    public int countSuccess(){
        return repository.countSuccess();
    }


}

