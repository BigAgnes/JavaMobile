package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            FOOTER_ELEMENT = "//*[@text = 'Посмотреть статью в браузере']",
            PRESENT_ELEMENT = "//*[contains(@text, 'Java')]",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MENU_BOOKMARK = "org.wikipedia:id/article_menu_bookmark",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[contains(@text, 'Добавить в другой список для чтения')]",
            ADD_NEW_MY_LIST = "org.wikipedia:id/create_button",
            MY_LIST_OK_BUTTON = "android:id/button1",
            BUTTON_BACK = "//android.widget.ImageButton[@content-desc='Перейти вверх']",
            BACK_IN_FIRST_PAGE = "//*[@class='android.widget.ImageButton']",
            MY_FOLDER_TPL = "//*[contains(@text, '{SUBSTRING}')]",
            TITLE = "//android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[2]";
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
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 10);
    }

    public String waitAndGetArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article", 20
                );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(MENU_BOOKMARK),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                By.id(MENU_BOOKMARK),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                By.id(ADD_NEW_MY_LIST),
                "The element was not found or it is impossible to click on it",
                15
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToMyFolder(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(MENU_BOOKMARK),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                By.id(MENU_BOOKMARK),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "The element was not found or it is impossible to click on it",
                15
        );
        String search_result_xpath = getResultSearchElement(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "The element was not found or it is impossible to click on it",
                15
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(BUTTON_BACK),
                "The element was not found or it is impossible to click on it",
                15
        );
    }

    public void back_in_first_page()
    {
        this.waitForElementAndClick(
                By.xpath(BACK_IN_FIRST_PAGE),
                "The element was not found or it is impossible to click on it",
                15
        );
    }

    public void assert_title_in_article(String expected_text)
    {
        this.waitForElementPresent(
                By.xpath(PRESENT_ELEMENT),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.waitForElementAndClick(
                By.xpath(PRESENT_ELEMENT),
                "The element was not found or it is impossible to click on it",
                15
        );
        this.assertElementHasText(
                By.xpath(TITLE),
                expected_text,
                "Text does not match"
        );
    }

    public void presentArticleTitle()
    {
        this.assertElementPresent(By.xpath(TITLE) ,"Title is not present this page");
    }
}

