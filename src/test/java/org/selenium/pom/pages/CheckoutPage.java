package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPostCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");
    private final By placeOrderBtn = By.xpath("//*[@id='place_order']");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        driver.findElement(addressLineOneField).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city){
        driver.findElement(billingCityField).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode){
        driver.findElement(billingPostCodeField).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        driver.findElement(billingEmailField).sendKeys(email);
        return this;
    }

    public CheckoutPage placeOrder (){
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
       return driver.findElement(successNotice).getText();
    }

    public CheckoutPage fillCheckoutForm(String firstName, String lastName, String addressLineOne,String city, String postalCode, String email){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddressLineOne(addressLineOne);
        enterCity(city);
        enterPostCode(postalCode);
        enterEmail(email);
        placeOrder();

        return this;
    }
}
