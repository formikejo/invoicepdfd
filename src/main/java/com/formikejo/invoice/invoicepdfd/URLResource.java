package com.formikejo.invoice.invoicepdfd;

import com.codahale.metrics.annotation.Timed;
import com.formikejo.invoice.invoicepdfd.view.InvoiceCreator;
import com.formikejo.invoice.invoicepdfd.view.InvoiceView;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.*;
import javax.ws.rs.*;
import javax.xml.parsers.*;
import javax.ws.rs.core.*;
import javax.xml.xpath.XPathExpressionException;
import java.net.URI;
import java.util.UUID;

@Path("/")
public class URLResource {

    InvoiceViewRepository repository;
    String addressOfURLPDFCreator;
    String addressOfThisServer;

    public URLResource(InvoiceViewRepository repository,String addressOfURLPDFCreator,String addressOfThisServer) {
        this.repository = repository;
        this.addressOfThisServer = addressOfThisServer;
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
        Document document = null;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        } catch (SAXException | IOException e) {
            throw new WebApplicationException("No XML provided", HttpStatus.BAD_REQUEST_400);
        }
        InvoiceView invV = new InvoiceCreator(document, imgStream, layoutScheme).getDataFromXML();

        URI uri = UriBuilder.fromUri(addressOfURLPDFCreator+"/pdf?url="+addressOfThisServer+"/invoice/" + repository.store(invV)).build();
        return Response.seeOther(uri).build();
    }

 }