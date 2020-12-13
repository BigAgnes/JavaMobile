package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static
    {
        FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";
        SEARCH_TITLE_BY_NUMBER_TPL = "xpath://android.view.ViewGroup[{NUMBER_BY_TITLE}]/android.widget.TextView[1]";
    }
    public AndroidMyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
