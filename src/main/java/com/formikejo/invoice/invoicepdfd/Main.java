package com.formikejo.invoice.invoicepdfd;


import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.media.multipart.MultiPartFeature;


public class Main extends Application<InvoiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void initialize(Bootstrap<InvoiceConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<InvoiceConfiguration>());
        bootstrap.addBundle(new AssetsBundle("/com/company", "/assets", "index.html"));
    }

    @Override
    public void run(InvoiceConfiguration configuration, Environment environment) {
        InvoiceResourceXML resource = new InvoiceResourceXML();
        environment.jersey().register(resource);
        environment.jersey().register(MultiPartFeature.class);
    }

}