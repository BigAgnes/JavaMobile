package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static
    {
        SKIP_IN_MY_SAVE = "id:Закрыть";
        SEARCH_TITLE_BY_NUMBER_TPL = "xpath://XCUIElementTypeCell[{NUMBER_BY_TITLE}]";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    }
    public IOSMyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
