package com.formikejo.invoice.invoicepdfd;

public class Receiver {

    private String name;
    private String streetName;
    private String postalCode;
    private String companyName;
    private String orderReferenceID;

    public Receiver(String nName, String nStreetName, String nPostalCode, String nCompanyName, String nOrderReferenceID) {
        name = nName;
        streetName = nStreetName;
        postalCode = nPostalCode;
        companyName = nCompanyName;
        orderReferenceID = nOrderReferenceID;
    }

    public String getName() {
        return name;
    }

    public String getOrderReferenceID() {
        return orderReferenceID;
    }

    public String getpostalCode() {
        return postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCompanyName() {
        return companyName;
    }
}
