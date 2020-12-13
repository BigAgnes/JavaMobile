package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
            FOLDER_BY_NAME_TPL,
            SKIP_IN_MY_SAVE,
            SEARCH_TITLE_BY_NUMBER_TPL,
            ARTICLE_BY_TITLE_TPL;

    public MyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getResultNumberTitleElement(String substring)
    {
        return SEARCH_TITLE_BY_NUMBER_TPL.replace("{NUMBER_BY_TITLE}", substring);
    }
    /* TEMPLATES METHODS */

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name  " + folder_name_xpath,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find save article"
        );
        if(Platform.getInstance().isIOS())
        {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find save article");
        }

        this.waitForArticleToDisappearByTitle(article_xpath);
    }

    public void swipeByNumberArticleToDelete(String article_title_number)
    {
        String article_locator = getResultNumberTitleElement(article_title_number);
        this.waitForElementPresent(article_locator);
        this.swipeElementToLeft(
                article_locator,
                "Cannot find save article"
        );
        if(Platform.getInstance().isIOS())
        {
            this.clickElementToTheRightUpperCorner(article_locator, "Cannot find save article");
        }

        this.waitForElementNotPresent(article_locator, "This element found in page", 15);
    }

    public void skipWarningInMySave()
    {
        this.waitForElementPresent(SKIP_IN_MY_SAVE, "Element not found", 15);
        this.waitForElementAndClick(SKIP_IN_MY_SAVE, "Cannot find and click skip button", 40);
        this.waitForElementNotPresent(SKIP_IN_MY_SAVE, "Button still present with title", 40);
    }
}
