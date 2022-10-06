package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By searchResultTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By addToCartBTN = By.xpath("//*[@data-product_id='1215']");
    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchField(String txt){
        driver.findElement(searchField).sendKeys(txt);
        return this;
    }

    public StorePage  clickSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }

    public String getSearchResultTitle(){
       return driver.findElement(searchResultTitle).getText();
    }

    public void  clickAddToCardBtn(){
        driver.findElement(addToCartBTN).click();
    }
}
