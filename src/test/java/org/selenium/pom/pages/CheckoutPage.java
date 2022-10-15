package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPostCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");
    private final By placeOrderBtn = By.xpath("//*[@id='place_order']");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.className("showlogin");
    private final By userNameField = By.id("username");
    private final  By passwordField = By.id("password");
    private final  By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName){
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        driver.findElement(addressLineOneField).clear();
        driver.findElement(addressLineOneField).sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city){
        driver.findElement(billingCityField).clear();
        driver.findElement(billingCityField).sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode){
        driver.findElement(billingPostCodeField).clear();
        driver.findElement(billingPostCodeField).sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        driver.findElement(billingEmailField).clear();
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

    public CheckoutPage clickHereToLoginLink(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(clickHereToLoginLink).click();
        return this;
    }

    public CheckoutPage enterUserName(String username){
        driver.findElement(userNameField).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        driver.findElement(loginBtn).click();
        return this;
    }

    public CheckoutPage login(String username, String password){
        return enterUserName(username)
                .enterPassword(password)
                .clickLoginBtn();
    }

    public CheckoutPage setBillingAddress (BillingAddress billingAddress){
       return enterFirstName(billingAddress.getFirstName()).
               enterLastName(billingAddress.getLastName()).
               enterAddressLineOne(billingAddress.getAddressLineOne()).
               enterCity(billingAddress.getCity()).
               enterPostCode(billingAddress.getPostalCode()).
               enterEmail(billingAddress.getEmail());
    }
}
