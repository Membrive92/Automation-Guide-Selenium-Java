package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SingUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Shopping")
@Feature("Adding to Checkout")
public class CheckoutTest extends BaseTest {

    @Story("Guest CHeckoout")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("tmslinkCheckout")
    @Issue("Issue: 2334 checkout")
    @Description("Atomic test: adding description ")
    @Test(description = "Checkout process obtain a product as Guest")
    public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load().
        setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();
        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Story("Login CHeckoout")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("tmslinkLogin")
    @Issue("Issue: 2334 Login")
    @Description("Atomic test: adding description ")
    @Test(description = "Checkout process obtain a product as logged user")
    public void LoginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        String username = ConfigLoader.getInstance().getUsername() + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword(ConfigLoader.getInstance().getPassword()).
                setEmail(ConfigLoader.getInstance().getEmail());

        SingUpApi signUpApi = new SingUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215,1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load().
        setBillingAddress(billingAddress).
                selectDirectBankTransfer().
                placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

}


