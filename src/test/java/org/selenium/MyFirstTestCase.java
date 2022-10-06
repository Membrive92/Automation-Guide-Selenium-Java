package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com");

        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.
                enterTextInSearchField("Blue")
                .clickSearchBtn();
        Assert.assertEquals(storePage.getSearchResultTitle(), "Search results: “Blue”");
        storePage.clickAddToCardBtn();


        driver.findElement(By.id("menu-item-1227")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(
                driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
                "Search results: “Blue”"
        );

        driver.findElement(By.xpath("//*[@data-product_id='1215']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title=\"View cart\"]")).click();
        Assert.assertEquals(
                driver.findElement(By.xpath("//*[@data-title=\"Product\"]")).getText(),
                "Blue Shoes"
        );

        driver.findElement(By.className("checkout-button")).click();
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.id("billing_email")).sendKeys("asko@gmail.com");
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(
                By.className("woocommerce-notice")).getText(),
                "Thank you. Your order has been received."
        );
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com");
        driver.findElement(By.id("menu-item-1227")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("woocommerce-product-search-field-0")).sendKeys("Blue");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(
                driver.findElement(By.cssSelector(".woocommerce-products-header__title.page-title")).getText(),
                "Search results: “Blue”"
        );

        driver.findElement(By.xpath("//*[@data-product_id='1215']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title=\"View cart\"]")).click();
        Assert.assertEquals(
                driver.findElement(By.xpath("//*[@data-title=\"Product\"]")).getText(),
                "Blue Shoes"
        );

        driver.findElement(By.className("checkout-button")).click();
        driver.findElement(By.className("showlogin")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("username")).sendKeys("fkuser2");
        driver.findElement(By.id("password")).sendKeys("fkpwd");
        driver.findElement(By.id("billing_first_name")).sendKeys("demo");
        driver.findElement(By.id("billing_last_name")).sendKeys("user");
        driver.findElement(By.id("billing_address_1")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_city")).sendKeys("San Francisco");
        driver.findElement(By.id("billing_postcode")).sendKeys("94188");
        driver.findElement(By.id("billing_email")).clear();
        driver.findElement(By.id("billing_email")).sendKeys("asko@gmail.com");
        driver.findElement(By.id("place_order")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(
                        By.className("woocommerce-notice")).getText(),
                "Thank you. Your order has been received."
        );
    }
}
