package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            FOOTER_ELEMENT,
            PRESENT_ELEMENT,
            MY_LIST_NAME_INPUT,
            MENU_BOOKMARK_IN_ARTICLE,
            MENU_BOOKMARK,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            ADD_NEW_MY_LIST,
            MY_LIST_OK_BUTTON,
            BUTTON_BACK,
            BACK_IN_FIRST_PAGE,
            MY_FOLDER_TPL,
            TITLE;

    public ArticlePageObject(AppiumDriver driver)
    {
     super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return MY_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent (TITLE, "Cannot find article title on page", 10);
    }

    public String waitAndGetArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid())
        {
            return title_element.getAttribute("text");
        }
        else
        {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid())
        {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of article", 40
            );
        }
        else
        {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                MENU_BOOKMARK_IN_ARTICLE,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                MENU_BOOKMARK_IN_ARTICLE,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                ADD_NEW_MY_LIST,
                "The element was not found or it is impossible to click on it",
                15
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToMyFolder(String name_of_folder)
    {
        this.waitForElementAndClick(
                MENU_BOOKMARK_IN_ARTICLE,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                MENU_BOOKMARK_IN_ARTICLE,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "The element was not found or it is impossible to click on it",
                15
        );
        String search_result_xpath = getResultSearchElement(name_of_folder);
        this.waitForElementAndClick(
                search_result_xpath,
                "The element was not found or it is impossible to click on it",
                15
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                BUTTON_BACK,
                "The element was not found or it is impossible to click on it",
                15
        );
    }

    public void back_in_first_page()
    {
        this.waitForElementAndClick(
                BACK_IN_FIRST_PAGE,
                "The element was not found or it is impossible to click on it",
                15
        );
    }

    public void assert_title_in_article(String expected_text)
    {
        this.waitForElementPresent(
                PRESENT_ELEMENT,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                PRESENT_ELEMENT,
                "The element was not found or it is impossible to click on it",
                15
        );
        this.assertElementHasText(
                TITLE,
                expected_text,
                "Text does not match"
        );
    }

    public void presentArticleTitle()
    {
        this.assertElementPresent(TITLE ,"Title is not present this page");
    }

    public void addArticlesToMySaved()
    {
        this.waitForElementAndClick(MENU_BOOKMARK_IN_ARTICLE, "Cannot find option to add article to reading list", 5);
    }


}

