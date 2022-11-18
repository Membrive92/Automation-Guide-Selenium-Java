package org.selenium.pom.tests;

import io.qameta.allure.Description;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Description("Atomic test: adding description ")
    @Test(description = "Using main menu to navigate to store page")
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().
                navigateToStoreUsingMenu();
        Assert.assertTrue(storePage.getSearchResultTitle("Store"));
    }
}
