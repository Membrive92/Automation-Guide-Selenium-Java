package org.selenium.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class HomePage extends BasePage {
   private final By storeMenuLink = By.id("menu-item-1227");

    public HomePage(WebDriver driver) {
        super(driver);

    }

    public HomePage load(){
        load("/");
        return this;
    }

    public StorePage navigateToStoreUsingMenu() throws InterruptedException {
        driver.findElement(storeMenuLink).click();
        Thread.sleep(2000);
        return new StorePage((driver));
    }
}
