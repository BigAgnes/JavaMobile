package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Step("Starting testCompareArticleTitle")
    @DisplayName("Compare article title with expected one")
    @Description("Clicking on the search field, entering text, searching, clicking on the article description (iOS click on the title), checking that the title matches the title from the search")
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.waitAndGetArticleTitle();

        Assert.assertEquals
                ("We see unexpected title!",
                        "Java",
                        article_title
                );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @Step("Starting testSwipeArticle")
    @DisplayName("Swipe article to the footer")
    @Description("Clicking on the search field, entering text, searching, clicking on the article description (iOS click on the title) and swipe on footer")
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature(value = "Search")
    @Step("Starting testGetTitleTest")
    @DisplayName("Get title in article")
    @Description("Clicking on the search field, entering text, searching, clicking on the article description (iOS click on the title) and get title")
    public void testGetTitleTest() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitAndGetArticleTitle();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Feature(value = "Search")
    @Step("Starting testSearchByArticleAndDescription")
    @DisplayName("Get title and description in article")
    @Description("Clicking on the search field, entering text, searching, clicking on the article description (iOS click on the title) and get title and description")
    public void testSearchByArticleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "программирования");
        }
    }

