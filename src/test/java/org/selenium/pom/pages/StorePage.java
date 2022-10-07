package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver.findElement(searchField).sendKeys(txt);
        return this;
    }

    public StorePage search(String txt) throws InterruptedException {
        enterTextInSearchField(txt).clickSearchBtn();
        Thread.sleep(2000);
        return this;
    }

    public StorePage  clickSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }

    public String getSearchResultTitle(){
       return driver.findElement(searchResultTitle).getText();
    }


    private By getAddToCartBtnElement(String productName){
        return By.xpath("//*[@aria-label='Add “"+ productName +"” to your cart']");
    }
    public StorePage  clickAddToCardBtn(String productName){
        By addToCartBTN = getAddToCartBtnElement(productName);
        driver.findElement(addToCartBTN).click();
        return this;
    }

    public CartPage clickViewCart(){
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }
}
