package OldTests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.*;



public class MyFirstClass extends CoreTestCase
{
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCheckingWordsInSearch()
    {
        MainPageObject.waitForElementAndClick(
                "org.wikipedia:id/fragment_onboarding_skip_button",
                "Not found element skip",
                15
        );
        MainPageObject.waitForElementAndClick(
                "//*[contains(@text, 'Поиск по Википедии')]",
                "The element was not found or it is impossible to click on it",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                "org.wikipedia:id/search_src_text",
                "java",
                "Element not found or unable to enter text",
                10
        );
        for(int i = 1; i < 5; i++)
        {
            WebElement element = MainPageObject.waitForElementPresent(
                    "//android.view.ViewGroup[" + i + "]/android.widget.TextView",
                    "Element not found",
                    15
            );
            String actual_text = element.getText();
            assertTrue("The expected text does not match the actual", actual_text.contains("Java")||actual_text.contains("java"));
        }
    }



}