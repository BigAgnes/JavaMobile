import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;


public class MyFirstClass {
    private AndroidDriver driver;
    @Before
    public void SetUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/kristina_maksimova/Desktop/JavaMobile/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void checkingWordsInSearch(){
        WebElement skip = waitForElementPresent(By.xpath("//*[contains (@text, 'SKIP')]"), "Not found", 15);
        skip.click();
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "The element was not found or it is impossible to click on it", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "java","Element not found or unable to enter text", 10);
        for(int i = 1; i < 5; i++){
            WebElement element = waitForElementPresent(By.xpath("//android.view.ViewGroup[" + i + "]/android.widget.TextView"), "Element not found", 15);
            String actual_text = element.getText();
            Assert.assertTrue("The expected text does not match the actual", actual_text.contains("Java")||actual_text.contains("java"));
        }
    }


    @Test
    public void findSeveralOptionsAndCancelTheSearch(){
        WebElement skip = waitForElementPresent(By.xpath("//*[contains (@text, 'SKIP')]"), "Not found", 15);
        skip.click();
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "The element was not found or it is impossible to click on it", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "java","Element not found or unable to enter text", 10);
        driver.findElements(By.xpath("//*[contains(@text, 'Java')]"));
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='Clear query']"), "The element was not found or it is impossible to click on it", 10);
        waitForElementNotPresent(By.id("Clear query"), "The element is displayed on the page", 10);
    }

    @Test
    public void compareTextInInputFieldSearch(){
        WebElement skip = waitForElementPresent(By.xpath("//*[contains (@text, 'SKIP')]"), "Not found", 15);
        skip.click();
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Element not found", 10);
        assertElementHasText(By.xpath("//*[contains(@text, 'Search Wikipedia')]"), "Search Wikipedia", "The expected text does not match the actual");
    }
    private void assertElementHasText(By by, String expected_text, String error_message){
        WebElement title_element = waitForElementPresent(by);
        String actual_text = title_element.getText();
        Assert.assertEquals(error_message, expected_text, actual_text);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 10);
    }
    private WebElement waitForElementPresent(By by){
        return waitForElementPresent(by, "Element not found", 10);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }




}
