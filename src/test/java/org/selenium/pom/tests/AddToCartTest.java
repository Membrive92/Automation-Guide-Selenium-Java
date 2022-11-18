package org.selenium.pom.tests;


import io.qameta.allure.Description;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.DataProviders;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Description("Atomic test: Adding a product from store page and the assert fail")
    @Test(description = "Add to cart from store page a product and it will fail")
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).load().
                getProductThumbnail().clickAddToCardBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), (product.getName()) + "dada");
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class)
    public void addToCartFeaturedProducts(Product product){
        CartPage cartPage = new HomePage(getDriver()).load().
                getProductThumbnail().
                clickAddToCardBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
