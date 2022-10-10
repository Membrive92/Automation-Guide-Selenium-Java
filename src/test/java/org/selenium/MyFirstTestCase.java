package org.selenium;


import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirstName("demo");
        billingAddress.setLastName("user");
        billingAddress.setAddressLineOne("San Francisco");
        billingAddress.setCity("San francisco");
        billingAddress.setPostalCode("94199");
        billingAddress.setEmail("fkuser@gmail.com");

        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getSearchResultTitle(), "Search results: “Blue”");
        
        storePage.clickAddToCardBtn("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                placeOrder();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
        Thread.sleep(2000);
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException {
        StorePage storePage = new HomePage(driver).
                load().
                navigateToStoreUsingMenu().
                search("Blue");
        Assert.assertEquals(storePage.getSearchResultTitle(), "Search results: “Blue”");

        storePage.clickAddToCardBtn("Blue Shoes");
        Thread.sleep(2000);
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");

        CheckoutPage checkoutPage = cartPage.checkout();
        Thread.sleep(3000);
        checkoutPage.clickHereToLoginLink();
        Thread.sleep(3000);

         checkoutPage.
                 login("userfk1","userfk2").
                 enterFirstName("demo").
                 enterLastName("user").
                 enterAddressLineOne("New york").
                 enterCity("new york").
                 enterPostCode("94199").
                 enterEmail("memb@gmail.com").
                placeOrder();
        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
        Thread.sleep(2000);
    }
}
