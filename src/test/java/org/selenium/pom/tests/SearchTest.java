package org.selenium.pom.tests;

import io.qameta.allure.Description;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Description("Atomic test: adding description ")
    @Test(description = "Searching Blue in a filter")
    public void searchWithPartialMatch(){
        String searchFor= "Search results: ”Blue”";
        StorePage storePage = new StorePage(getDriver())
                .load().
                search(searchFor);
        Assert.assertTrue(storePage.getSearchResultTitle(searchFor));
    }

}
