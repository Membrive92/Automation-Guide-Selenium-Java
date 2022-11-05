package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SingUpApi;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @Test
    public void loginDuringCheckout() throws IOException {
        String username = "apiuser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("apiuserpass").
                setEmail(username + "@memb.com");

        SingUpApi signUpApi = new SingUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);
    }
}
