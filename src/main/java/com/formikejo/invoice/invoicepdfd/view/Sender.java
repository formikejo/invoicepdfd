package com.formikejo.invoice.invoicepdfd.view;


public class Sender {

    private String name;
    private String address;
    private String email;
    private String telephone;
    private String companyName;
    private String postalCode;
    private String companyIdentification;
    private String bankAccount;
    private String vatNumber;

    public Sender(String nName, String nAddress, String nPostalCode, String nEmail, String nTelephone, String nCompanyName, String nCompanyIdentification, String nBankAccount, String nVatNumber) {
        name = nName;
        address = nAddress;
        email = nEmail;
        postalCode = nPostalCode;
        telephone = nTelephone;
        companyName = nCompanyName;
        companyIdentification = nCompanyIdentification;
        bankAccount = nBankAccount;
        vatNumber = nVatNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getvatNumber() {
        return vatNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getBankAccount() {
        return bankAccount;
    }


    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyIdentification() {
        return companyIdentification;
    }

}
