package org.selenium.pom.objects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String addressLineOne;
    private String city;
    private String postalCode;


    public BillingAddress(){}

    public BillingAddress(String firstName, String lastName, String addressLineOne, String city, String postalCode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
    }

    private String email;

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddressLineOne() {
        return addressLineOne;

    }

    public BillingAddress setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
         this.email = email;
         return this;
    }
}
