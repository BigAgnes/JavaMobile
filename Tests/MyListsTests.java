package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;



public class MyListsTests extends CoreTestCase {
    private static final String
            name_of_folder = "Learning programming";

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Features(value = {@Feature(value = "Save in list"), @Feature(value = "Swipe for delete article")})
    @DisplayName("Adding an article to the saved list and deleting it")
    @Description("Save and delete one article")
    @Step("Starting testSaveFirstArticleToMyList")
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.waitAndGetArticleTitle();

        if(Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
            //ArticlePageObject.back_in_first_page();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(article_title);
        }
        else if (Platform.getInstance().isIOS())
        {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            ArticlePageObject.back_in_first_page();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            MyListsPageObject.skipWarningInMySave();
            MyListsPageObject.swipeByArticleToDelete(article_title.substring(0,1).toUpperCase() + article_title.substring(1).toLowerCase());
        }
        else {
            System.out.println("Test not using for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Features(value = {@Feature(value = "Save in list"), @Feature(value = "Swipe for delete article")})
    @DisplayName("Adding two articles to the saved list and deleting one")
    @Description("We save two articles and delete one of them")
    @Step("Starting testSwipeArticle")
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitAndClickOnArticleByNumber("1");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.waitAndGetArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.waitAndClickOnArticleByNumber("2");

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            String article_title_second = ArticlePageObject.waitAndGetArticleTitle();
            ArticlePageObject.addArticleToMyFolder(name_of_folder);
            ArticlePageObject.closeArticle();
            //ArticlePageObject.closeArticle();
            //ArticlePageObject.back_in_first_page();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();

            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(article_title);

            SearchPageObject.assertThereIsNoResultOfSearch(article_title);

            ArticlePageObject.assert_title_in_article(article_title_second);
        } else if (Platform.getInstance().isIOS())
        {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            SearchPageObject.waitAndClickOnArticleByNumber("3");

            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            ArticlePageObject.back_in_first_page();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();
            MyListsPageObject.skipWarningInMySave();

            MyListsPageObject.swipeByNumberArticleToDelete("2");

            SearchPageObject.assertThereIsNoResultOfSearch(article_title);
            SearchPageObject.waitAndClickOnArticleByNumber("1");
        }
        else
        {
            System.out.println("Test not using for platform: " + Platform.getInstance().getPlatformVar());
        }
    }
}
