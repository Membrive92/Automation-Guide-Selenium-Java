package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SingUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
@Epic("Shopping")
@Feature("Login")
public class LoginTest extends BaseTest {



    @Story("Login during checkout")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("LoginDuring")
    @Issue("Issue: 2334 LoginDuring")
    @Description("Atomic test: adding description ")
    @Test(description = "Login during checkout process Api way")
    public void loginDuringCheckout() throws Exception {
        String username = ConfigLoader.getInstance().getUsername() + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword(ConfigLoader.getInstance().getPassword()).
                setEmail(ConfigLoader.getInstance().getEmail());

        SingUpApi signUpApi = new SingUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.
                clickHereToLoginLink().
                login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
