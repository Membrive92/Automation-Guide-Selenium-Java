package org.selenium.pom.tests;


import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;

public class MyFirstTestCase extends BaseTest {
   // @Test
   @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
      String searchFor = "blue";
      BillingAddress  billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
      Product product = new Product(1215);

      StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();
      storePage.isLoaded();
      storePage.search(searchFor);
        Assert.assertTrue(storePage.getSearchResultTitle(searchFor));
        
        storePage.clickAddToCardBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        cartPage.isLoaded();
       Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.
                checkout().
                setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
       Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        String searchFor = "blue";
        BillingAddress  billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(),ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu().
                search(searchFor);
       Assert.assertTrue(storePage.getSearchResultTitle(searchFor));

        storePage.clickAddToCardBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), (product.getName()));

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.clickHereToLoginLink();

           checkoutPage.
                   login(user.getUsername(), user.getPassword()).
                   setBillingAddress(billingAddress).
                   selectDirectBankTransfer().
                   placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }
}
