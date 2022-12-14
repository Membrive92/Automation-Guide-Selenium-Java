package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

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
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By alternateCountryDropDown = By.id("select2-billing_country-container");
    private final By alternateStateDropDown = By.id("select2-billing_state-container");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    private final By productName = By.cssSelector("td[class='product-name']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load(){
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName){
        // Using method defined in the class
        WebElement elementVisible = waitForElementToBeVisible(firstNameField);
        elementVisible.clear();
        elementVisible.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        //Using ExpectedConditions without defined method
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        elementVisible.clear();
        elementVisible.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){

       /* Chrome option
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryDropDown)));
        select.selectByVisibleText(countryName); */

        //firefox option because there is an open bug in firefox
        wait.until((ExpectedConditions.elementToBeClickable(alternateCountryDropDown))).click();
        WebElement country = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='"+ countryName +"']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", country);
        country.click();
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneField));
        elementVisible.clear();
        elementVisible.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city){
        WebElement elementVisible = wait.until(ExpectedConditions.elementToBeClickable(billingCityField));
        elementVisible.clear();
        elementVisible.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName){
     /*   Chrome option
       Select select = new Select(driver.findElement(stateDropDown));
        select.selectByVisibleText(stateName);*/


        // firefox option
        wait.until((ExpectedConditions.elementToBeClickable(alternateStateDropDown))).click();
        WebElement state = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='"+ stateName +"']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", state);
        state.click();
        return this;
    }
    public CheckoutPage enterPostCode(String postCode){
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeField));
        elementVisible.clear();
        elementVisible.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailField));
        elementVisible.clear();
        elementVisible.sendKeys(email);
        return this;
    }


    @Step("Click on place order btn")
    public CheckoutPage placeOrder (){
       waitForOverlaysToDisappear(overlay);
       driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink(){
        waitForOverlaysToDisappear(overlay);
        WebElement elementVisible = wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink));
        elementVisible.click();
        return this;
    }

    public CheckoutPage enterUserName(String username){
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
        elementVisible.clear();
        elementVisible.sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        elementVisible.clear();
        elementVisible.sendKeys(password);
        return this;
    }

    @Step("Click on login btn")
    public CheckoutPage clickLoginBtn(){
        WebElement elementVisible = wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        elementVisible.click();
        return this;
    }

    private CheckoutPage waitForLoginBtnToDisappear(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    @Step("Login in the web")
    public CheckoutPage login(User user){
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLoginBtn().waitForLoginBtnToDisappear();
    }

    @Step("Select direct bank transfer method")
    public CheckoutPage selectDirectBankTransfer(){
        WebElement elementVisible = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if (!elementVisible.isSelected()){
            elementVisible.click();
        }
        return this;
    }

    public String getProductName() throws Exception {
        int i = 5;
        while (i > 0){
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
            } catch (StaleElementReferenceException e){
                System.out.println("NOT FOUND. TRYING AGAIN " + e);
            }
            Thread.sleep(5000);
            i--;
        }
       throw  new Exception("Element not found");
    }

    @Step
    public CheckoutPage setBillingAddress (BillingAddress billingAddress){
       return enterFirstName(billingAddress.getFirstName()).
               enterLastName(billingAddress.getLastName()).
               selectCountry(billingAddress.getCountry()).
               enterAddressLineOne(billingAddress.getAddressLineOne()).
               enterCity(billingAddress.getCity()).
               selectState(billingAddress.getState()).
               enterPostCode(billingAddress.getPostalCode()).
               enterEmail(billingAddress.getEmail());
    }
}
