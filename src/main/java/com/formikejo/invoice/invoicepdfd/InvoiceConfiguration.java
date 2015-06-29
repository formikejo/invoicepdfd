package com.formikejo.invoice.invoicepdfd;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class InvoiceConfiguration extends Configuration {

    @NotEmpty
    private String addressOfURLPDFCreator = "http://192.168.1.8:8080";
    @NotEmpty
    private String addressOfThisServer = "http://192.168.1.28:8080";

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