package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @Feature(value = "Search")
    @DisplayName("Search and open an article")
    @Description("Find an article by its description in the search")
    @Step("Starting testSearch")
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("программирования");
    }

    @Test
    @Severity(value = SeverityLevel.TRIVIAL)
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Cancel button")})
    @Step("Starting testCancelSearch")
    @DisplayName("The delete button is hidden after deleting the text in the search")
    @Description("Find articles and click on the next button X to make sure that the button is missing")
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @Feature(value = "Search")
    @Step("Starting testAmountNotEmptySearch")
    @DisplayName("Search result greater than 0")
    @Description("Make sure that the search results are greater than 0")
    public void testAmountNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "YouTube";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_article = SearchPageObject.getAmountOfFindArticles(search_line);

        Assert.assertTrue("We found two few results", amount_of_search_article>0);
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    @Feature(value = "Label empty search")
    @Step("Starting testAmountOfEmptySearch")
    @DisplayName("Icon in the absence of search results")
    @Description("Make sure that if the search result is empty, there is an icon with the inscription \" No result\"")
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "fgdgshhsyys";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        //SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Cancel button")})
    @Step("Starting testCompareTextInInputFieldSearch")
    @DisplayName("The search result is greater than zero and the button is missing after you click it")
    @Description("Search then click on the delete result button and make sure that the button X is missing")
    public void testCompareTextInInputFieldSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.searchManyResults("Java");
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
}
