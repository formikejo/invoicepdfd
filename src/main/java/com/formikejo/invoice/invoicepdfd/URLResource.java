package com.formikejo.invoice.invoicepdfd;

import com.codahale.metrics.annotation.Timed;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.InputStream;
import java.net.URI;

@Path("/")
public class URLResource {

    private InvoiceService invoiceService;
    String addressOfURLPDFCreator;

    public URLResource(InvoiceViewRepository repository,String addressOfURLPDFCreator,String addressOfThisServer) {
        this.invoiceService = new InvoiceService(repository, addressOfThisServer);
        this.addressOfURLPDFCreator = addressOfURLPDFCreator;
    }

    @Timed
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/pdf")
    public Response processInvoice(
            @FormDataParam("xml") final InputStream inputStream,
            @FormDataParam("xml") final FormDataContentDisposition contentDispositionHeader,
            @FormDataParam("img") final String imgStream,
            @FormDataParam("layoutScheme") final String layoutScheme
    ) throws ParserConfigurationException, XPathExpressionException {
        URI htmlView = invoiceService.getInvoiceURI(inputStream, imgStream, layoutScheme);
        URI uri = UriBuilder.fromUri(addressOfURLPDFCreator+"/pdf?url="+htmlView.toString()).build();
        return Response.seeOther(uri).build();
    }

 }