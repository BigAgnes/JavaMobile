package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                FOOTER_ELEMENT = "xpath://*[@text = 'Посмотреть статью в браузере']";
                PRESENT_ELEMENT = "xpath://*[contains(@text, 'Java')]";
                MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                MENU_BOOKMARK_IN_ARTICLE = "id:org.wikipedia:id/article_menu_bookmark";
                OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[contains(@text, 'Добавить в другой список для чтения')]";
                ADD_NEW_MY_LIST = "id:org.wikipedia:id/create_button";
                MY_LIST_OK_BUTTON = "id:android:id/button1";
                BUTTON_BACK = "xpath://android.widget.ImageButton[@content-desc='Перейти вверх']";
                BACK_IN_FIRST_PAGE = "xpath://*[@class='android.widget.ImageButton']";
                MY_FOLDER_TPL = "xpath://*[contains(@text, '{SUBSTRING}')]";
                TITLE = "xpath://android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
