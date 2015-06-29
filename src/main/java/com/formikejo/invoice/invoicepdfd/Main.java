package com.formikejo.invoice.invoicepdfd;


import com.codahale.metrics.Gauge;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import static com.codahale.metrics.MetricRegistry.name;


public class Main extends Application<InvoiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void initialize(Bootstrap<InvoiceConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<InvoiceConfiguration>());
        bootstrap.addBundle(new AssetsBundle("/assets", "/assets"));
    }

    @Override
    public void run(InvoiceConfiguration configuration, Environment environment) {
        InvoiceViewRepository repository = new InvoiceViewRepository();

        InvoiceResource resource = new InvoiceResource(repository);
        environment.jersey().register(resource);

        URLResource resource1 = new URLResource(repository, configuration.getAddressOfURLPDFCreator(), configuration.getAddressOfThisServer());
        environment.jersey().register(resource1);


        environment.jersey().register(MultiPartFeature.class);

        environment.metrics().register(name(InvoiceViewRepository.class, "successCount"), new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return repository.countSuccess();
            }
        });
    }

}