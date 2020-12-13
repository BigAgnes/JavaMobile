package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{
    private static final String
        STEP_LEARN_MORE_LINK = "name:Узнать подробнее о Википедии",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:Новые способы изучения",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "name:Добавить или изменить предпочтительные языки",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Помогите сделать это приложение лучше",
        NEXT_LINK = "name:Далее",
        GET_STARTED_BUTTON = "name:Начать",
        SKIP_BUTTON_FOR_IOS = "id:Пропустить",
        SKIP_BUTTON_FOR_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' button", 10);
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred text' link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' button", 10);
    }

    public void clickSkipIos()
    {
        this.waitForElementAndClick(SKIP_BUTTON_FOR_IOS, "Cannot find and click skip button", 10);
    }

    public void clickSkipAndroid()
    {
        this.waitForElementAndClick(SKIP_BUTTON_FOR_ANDROID, "Cannot find and click skip button", 10);
    }
}
