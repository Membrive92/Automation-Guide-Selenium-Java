package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {
    @FindBy(css = "td[class='product-name'] a")
    private WebElement productName;
    @FindBy(css = ".checkout-button")
    //this annotation cached an element in cache
    @CacheLookup
    private WebElement checkoutBtn;
    @FindBy(how = How.CSS, using = ".has-text-align-center")
    private WebElement cartHeading;
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isLoaded(){
       return wait.until(ExpectedConditions.textToBePresentInElement(cartHeading, "Cart"));
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }
}
