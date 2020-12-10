package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{
    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Поиск по Википедии')]",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL = "xpath://*[contains(@text, '{SUBSTRING}')]",
            SEARCH_EMPTY_RESULT_ELEMENT = "id:org.wikipedia:id/results_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://android.view.ViewGroup/android.widget.TextView[@text = '{SUBSTRING}']",
            SEARCH_TITLE_BY_NUMBER_TPL = "xpath://android.view.ViewGroup[{NUMBER_BY_TITLE}]/android.widget.TextView[1]",
            SEARCH_RESULT_BY_SUBSTRING_TITLE_AND_DESCRIPTION_TPL = "xpath://androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[contains(@text, '{TITLE}')]/../android.widget.TextView[contains(@text, '{DESCRIPTION}')]";

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

    private static String getResultSearchElementWithTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}",  title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line)
    {
       this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result substring " + substring);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is steel present",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFindArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL,
                "Cannot find element by the request",
                15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL);
    }

    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL, "We supposed not to find any result", 10);
    }

    public void searchManyResults(String substring)
    {
        String search_result_xpath = getResultElement(substring);
        By by = this.getLocatorByString(search_result_xpath);
        driver.findElements(by);
    }

    public void waitAndClickOnArticleByNumber(String substring)
    {
        String search_result_xpath = getResultNumberTitleElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search init element", 15);
    }

   public void waitForElementByTitleAndDescription(String title, String description)
   {
       String search_result_xpath = getResultSearchElementWithTitleAndDescription(title, description);
       for(int i = 1; i <= 3; i++)
       {
           WebElement element = this.waitForElementPresent(
                   search_result_xpath.replace("1", Integer.toString(i)),
                   "Item does not have the same title and/or description",
                   15
           );
       }
   }
}



