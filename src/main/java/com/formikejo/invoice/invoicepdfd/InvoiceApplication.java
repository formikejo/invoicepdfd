package com.formikejo.invoice.invoicepdfd;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class InvoiceApplication extends Application<InvoiceConfiguration> {

	@Override
	public void run(InvoiceConfiguration invoiceConfiguration, Environment environment) throws Exception {

	}

	public static void main(String... args) throws Exception {
		new InvoiceApplication().run(args);
	}
}
