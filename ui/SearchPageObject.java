package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Поиск по Википедии')]",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL = "//*[contains(@text, '{SUBSTRING}')]",
            SEARCH_EMPTY_RESULT_ELEMENT = "org.wikipedia:id/results_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//android.view.ViewGroup/android.widget.TextView[@text = '{SUBSTRING}']",
            SEARCH_TITLE_BY_NUMBER_TPL = "//android.view.ViewGroup[{NUMBER_BY_TITLE}]/android.widget.TextView[1]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultElement(String substring)
    {
        return SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultNumberTitleElement(String substring)
    {
        return SEARCH_TITLE_BY_NUMBER_TPL.replace("{NUMBER_BY_TITLE}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.id(SEARCH_INPUT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line)
    {
       this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result substring " + substring);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is steel present",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFindArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL),
                "Cannot find element by the request",
                15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL));
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.waitForElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL), "We supposed not to find any result", 10);
    }

    public void searchManyResults(String substring)
    {
        String search_result_xpath = getResultElement(substring);
        driver.findElements(By.xpath(search_result_xpath));
    }

    public void waitAndClickOnArticleByNumber(String substring)
    {
        String search_result_xpath = getResultNumberTitleElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search init element", 15);
    }

}
