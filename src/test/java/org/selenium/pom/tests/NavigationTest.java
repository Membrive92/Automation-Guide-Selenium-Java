package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Shopping")
@Feature("Navigation")
public class NavigationTest extends BaseTest {

    @Story("Navigation to Home")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Navigate")
    @Issue("Issue: 2334 Navigate")
    @Description("Atomic test: adding description ")
    @Test(description = "Using main menu to navigate to store page")
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().getMyHeader().
                navigateToStoreUsingMenu();
        Assert.assertTrue(storePage.getSearchResultTitle("Store"));
    }
}
