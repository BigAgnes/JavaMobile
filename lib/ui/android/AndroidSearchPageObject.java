package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
     static {
         SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Поиск по Википедии')]";
         SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
         SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
         SEARCH_RESULT_ELEMENT_BY_SUBSTRING_TPL = "xpath://*[contains(@text, '{SUBSTRING}')]";
         SEARCH_EMPTY_RESULT_ELEMENT = "id:org.wikipedia:id/results_text";
         SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://android.view.ViewGroup/android.widget.TextView[contains(@text, '{SUBSTRING}')]";
         SEARCH_TITLE_BY_NUMBER_TPL = "xpath://android.view.ViewGroup[{NUMBER_BY_TITLE}]/android.widget.TextView[1]";
         SEARCH_RESULT_BY_SUBSTRING_TITLE_AND_DESCRIPTION_TPL = "xpath://androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[contains(@text, '{TITLE}')]/../android.widget.TextView[contains(@text, '{DESCRIPTION}')]";
     }
    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
