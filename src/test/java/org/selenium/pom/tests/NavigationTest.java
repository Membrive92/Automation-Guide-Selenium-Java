package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().
                navigateToStoreUsingMenu();
        Assert.assertTrue(storePage.getSearchResultTitle("Store"));
    }
}
