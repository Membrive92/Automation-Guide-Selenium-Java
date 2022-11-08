package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtnElement(String productName){
        return By.xpath("//*[@aria-label='Add “"+ productName +"” to your cart']");
    }
    public ProductThumbnail clickAddToCardBtn(String productName){
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
