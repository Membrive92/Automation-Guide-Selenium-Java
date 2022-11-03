package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyClass {

    public static void main(String[] args){
        String username = "apiuser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("apiuserpass").
                setEmail(username + "@memb.com");
        SingUpApi signUpApi = new SingUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER COOKIES " + signUpApi.getCookies());
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215,1);
        System.out.println("CART COOKIES " + cartApi.getCookies());

    }
}
