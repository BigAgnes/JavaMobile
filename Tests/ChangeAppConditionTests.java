package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Feature(value = "Oriental screen")
    @Step("Starting testChangeOrientationOfSearchResult")
    @DisplayName("Comparison of the article title in portrait and landscape orientation")
    @Description("Search by article, open, take the title, flip the device, take the title and return the device back, compare the results")
    public void testChangeOrientationOfSearchResult()
    {
        if(Platform.getInstance().isMW())
        {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("программирования");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.waitAndGetArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.waitAndGetArticleTitle();
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.waitAndGetArticleTitle();
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature(value = "Screen of background")
    @Step("Starting testCheckSearchArticleInBackground")
    @DisplayName("The search goes into the background")
    @Description("Search by article, open, wait for the title, send the app to the background, return, make sure that the title remains on the screen")
    public void testCheckSearchArticleInBackground()
    {
        if(Platform.getInstance().isMW())
        {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("программирования");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("программирования");
    }
}
