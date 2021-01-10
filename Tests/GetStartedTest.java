package Tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Feature(value = "Welcome page")
    @Step("Starting testPassTroughWelcome")
    @DisplayName("The welcome menu for iOS")
    @Description("Go through the welcome menu for the platform iOS")
    public void testPassTroughWelcome()
    {
        if((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW()))
        {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);
        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}
