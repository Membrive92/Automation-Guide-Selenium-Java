package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Shopping")
@Feature("Searching")
public class SearchTest extends BaseTest {

    @Story("Searching at Store")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("Search")
    @Issue("Issue: 2334 Search")
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
