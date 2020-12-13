package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Поиск по Википедии']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@label='Поиск по Википедии']";
        SEARCH_CANCEL_BUTTON = "id:Очистить текст";
        SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='Ничего не найдено']"; //пустой результат
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]"; //2
        SEARCH_TITLE_BY_NUMBER_TPL = "xpath://XCUIElementTypeCell[{NUMBER_BY_TITLE}]";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeLink[contains(@name, '{TITLE}')]/../XCUIElementTypeLink[contains(@name, '{DESCRIPTION}')]";
    }
    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
