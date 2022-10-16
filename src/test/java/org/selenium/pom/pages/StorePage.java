package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By searchResultTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        return this;
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    public StorePage search(String txt){
        enterTextInSearchField(txt).clickSearchBtn();
        return this;
    }

    public StorePage clickSearchBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getSearchResultTitle(){
        wait.until(ExpectedConditions.textToBe(searchResultTitle, "Search results: “blue”"));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultTitle)).getText();
    }

    private By getAddToCartBtnElement(String productName){
        return By.xpath("//*[@aria-label='Add “"+ productName +"” to your cart']");
    }
    public StorePage  clickAddToCardBtn(String productName){
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
