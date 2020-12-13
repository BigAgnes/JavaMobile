package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        FOOTER_ELEMENT = "id:Просмотреть статью в браузере";
        PRESENT_ELEMENT = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name, 'Java')]";
        MENU_BOOKMARK_IN_ARTICLE = "id:Сохранить на потом";
        MENU_BOOKMARK = "id:Сохранено";
        BUTTON_BACK = "id:Поиск";
        BACK_IN_FIRST_PAGE = "id:Отменить";
        TITLE = "id:Java";
    }
    public IOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
