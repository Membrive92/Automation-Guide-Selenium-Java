package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnail;

public class WomenPage extends BasePage {
    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By searchResultTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
    private ProductThumbnail productThumbnail;

    private final String addToCartDynamicBtn = "//*[@data-product_id='%s']";

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public WomenPage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }


    public WomenPage enterTextInSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        return this;
    }

    @Step("Load Store page")
    public WomenPage load(){
        load("/product-category/women/");
        return this;
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.urlContains("/product-category/women/"));
    }

    @Step("Enter text to search field")
    public WomenPage search(String txt){
        enterTextInSearchField(txt).clickSearchBtn();
        return this;
    }

    @Step("Click to search btn")
    public WomenPage clickSearchBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    @Step("Obtain text from the result search")
    public boolean getSearchResultTitle(String searchResult){
      return  wait.until(ExpectedConditions.textToBePresentInElementLocated(searchResultTitle, searchResult));
    }

    @Step("Click on btn product using dynamic xpath")
    public WomenPage clickDynamicProductBtn(String addToCartBtn) {
        WebElement addToCartProductBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(addToCartDynamicBtn, addToCartBtn))));
        addToCartProductBtn.click();
        return this;
    }
}
