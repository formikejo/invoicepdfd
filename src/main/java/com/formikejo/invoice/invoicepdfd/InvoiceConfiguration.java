package com.formikejo.invoice.invoicepdfd;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class InvoiceConfiguration extends Configuration {

    @NotEmpty
    private String addressOfURLPDFCreator;

    @NotEmpty
    private String addressOfThisServer;

    @JsonProperty
    public String getAddressOfThisServer() {
        return addressOfThisServer;
    }

    @JsonProperty
    public void setAddressOfThisServer(String addressOfThisServer) {
        this.addressOfThisServer = addressOfThisServer;
    }

    @JsonProperty
    public String getAddressOfURLPDFCreator() {
        return addressOfURLPDFCreator;
    }

    @JsonProperty
    public void setAddressOfURLPDFCreator(String addressOfURLPDFCreator) {
        this.addressOfURLPDFCreator = addressOfURLPDFCreator;
    }
}