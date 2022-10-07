package org.selenium;

import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
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
        Thread.sleep(2000);
        storePage.search("Blue");
        Thread.sleep(2000);
        Assert.assertEquals(storePage.getSearchResultTitle(), "Search results: “Blue”");
        
        storePage.clickAddToCardBtn("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillCheckoutForm(
                "demo",
                "user",
                "New york",
                "new york",
                "94199",
                "fkuser@gmail.com"
        );

       /* checkoutPage.
                enterFirstName("demo").
                enterLastName("user").
                enterAddressLineOne("New york").
                enterCity("new york").
                enterPostCode("94199").
                enterEmail("memb@gmail.com").
                placeOrder();*/

        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
        Thread.sleep(2000);
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
