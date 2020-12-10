package Tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;


public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("язык программирования");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.waitAndGetArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.back_in_first_page();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitAndClickOnArticleByNumber("1");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String name_of_folder = "Learning programming";
        String article_title = ArticlePageObject.waitAndGetArticleTitle();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        SearchPageObject.waitAndClickOnArticleByNumber("2");
        String article_title_second = ArticlePageObject.waitAndGetArticleTitle();
        ArticlePageObject.addArticleToMyFolder(name_of_folder);
        ArticlePageObject.closeArticle();
        ArticlePageObject.closeArticle();

        ArticlePageObject.back_in_first_page();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);

        ArticlePageObject.assert_title_in_article(article_title_second);
    }
}
