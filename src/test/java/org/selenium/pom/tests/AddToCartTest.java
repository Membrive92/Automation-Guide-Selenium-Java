package org.selenium.pom.tests;


import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                clickAddToCardBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), (product.getName()));
    }

    @Test
    public void addToCartFeaturedProducts(){
        CartPage cartPage = new HomePage(getDriver()).load().
                clickAddToCardBtn("Blue Shoes").
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), "Blue Shoes");
    }
}
