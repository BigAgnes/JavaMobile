package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;


public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
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
        }
        else
        {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();
        ArticlePageObject.back_in_first_page();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid())
        {
            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(article_title);
        }
        else
        {
            MyListsPageObject.skipWarningInMySave();
            MyListsPageObject.swipeByArticleToDelete(article_title.substring(0,1).toUpperCase() + article_title.substring(1).toLowerCase());
        }
    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitAndClickOnArticleByNumber("1");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            String article_title = ArticlePageObject.waitAndGetArticleTitle();
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            SearchPageObject.waitAndClickOnArticleByNumber("3");
            String article_title_second = ArticlePageObject.waitAndGetArticleTitle();
            ArticlePageObject.addArticleToMyFolder(name_of_folder);
            ArticlePageObject.closeArticle();
            ArticlePageObject.closeArticle();
            ArticlePageObject.back_in_first_page();

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyLists();

            MyListsPageObject.openFolderByName(name_of_folder);
            MyListsPageObject.swipeByArticleToDelete(article_title);

            ArticlePageObject.assert_title_in_article(article_title_second);
        } else {
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

            SearchPageObject.waitAndClickOnArticleByNumber("1");
        }
    }
}
