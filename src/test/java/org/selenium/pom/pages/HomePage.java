package org.selenium.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
   private final By storeMenuLink = By.id("menu-item-1227");
   private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public HomePage(WebDriver driver) {
        super(driver);

    }

    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public StorePage navigateToStoreUsingMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage((driver));
    }

    private By getAddToCartBtnElement(String productName){
        return By.xpath("//*[@aria-label='Add “"+ productName +"” to your cart']");
    }
    public HomePage  clickAddToCardBtn(String productName){
        By addToCartBTN = getAddToCartBtnElement(productName);
        WebElement elementVisible = wait.until(ExpectedConditions.elementToBeClickable(addToCartBTN));
        elementVisible.click();
        return this;
    }

    public CartPage clickViewCart(){
        WebElement elementVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink));
        elementVisible.click();
        return new CartPage(driver);
    }
}
