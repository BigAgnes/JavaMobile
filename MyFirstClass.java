import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;
import java.util.List;


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
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void getTitleTest(){
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Not found element skip", 15);  //нажатие кнопки "Пропустить"
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Поиск по Википедии')]"), "The element was not found or it is impossible to click on it", 5); //нажатие на "поиск"
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java","Element not found or unable to enter text", 10);  //ввод названия статьи
        waitForElementAndClick(By.xpath("//android.view.ViewGroup[1]/android.widget.TextView"), "The element was not found or it is impossible to click on it", 15); //нажатие на первую статью в списке
        assertElementPresent(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[1][contains(@text, 'Java')]"),"Title is not present this page");

    }

    @Test
    public void testSwipeArticle(){
        String my_list = "Learning programming";  //переменная для ханения названия списка
        String name_article = "Java";  //переменная для названия статьи
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"), "Not found element skip", 15);  //нажатие кнопки "Пропустить"
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Поиск по Википедии')]"), "The element was not found or it is impossible to click on it", 5); //нажатие на "поиск"
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), name_article,"Element not found or unable to enter text", 10);  //ввод названия статьи
        waitForElementAndClick(By.xpath("//android.view.ViewGroup[1]/android.widget.TextView"), "The element was not found or it is impossible to click on it", 15); //нажатие на первую статью в списке
        waitForElementAndClick(By.id("org.wikipedia:id/article_menu_bookmark"), "The element was not found or it is impossible to click on it", 15); //нажатие на "добавить в список"
        waitForElementAndClick(By.id("org.wikipedia:id/article_menu_bookmark"), "The element was not found or it is impossible to click on it", 15); //повторное нажатие, так как не происходит с первого раза
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Добавить в другой список для чтения')]"), "The element was not found or it is impossible to click on it", 15); //нажатие на выбор куда добавить
        waitForElementAndClick(By.id("org.wikipedia:id/create_button"), "The element was not found or it is impossible to click on it", 15); //нажатие на кнопку создание списка
        waitForElementAndClick(By.id("org.wikipedia:id/text_input"), "The element was not found or it is impossible to click on it", 15); //нажатие на поле для ввода названия
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), my_list,"The element was not found or it is impossible to click on it", 15);  //ввод названия списка
        waitForElementAndClick(By.id("android:id/button1"), "The element was not found or it is impossible to click on it", 15); //нажатие на кнопку ОК
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"), "The element was not found or it is impossible to click on it", 15); //нажатие на кнопку назад к поиску по статьям
        waitForElementAndClick(By.xpath("//android.view.ViewGroup[2]/android.widget.TextView"), "The element was not found or it is impossible to click on it", 15);  //нажатие на вторую найденную статью
        waitForElementAndClick(By.id("org.wikipedia:id/article_menu_bookmark"), "The element was not found or it is impossible to click on it", 15); //нажатие на "добавить в список"
        waitForElementAndClick(By.id("org.wikipedia:id/article_menu_bookmark"), "The element was not found or it is impossible to click on it", 15);  //повторное нажатие, так как не происходит с первого раза
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Добавить в другой список для чтения')]"), "The element was not found or it is impossible to click on it", 15); //нажатие на выбор куда добавить
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Learning programming')]"), "The element was not found or it is impossible to click on it", 15); //нажатие на ранее созданный список
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"), "The element was not found or it is impossible to click on it", 15); //нажатие на кнопку назад
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Перейти вверх']"), "The element was not found or it is impossible to click on it", 15); //повторное нажатие на кнопку назад
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "The element was not found or it is impossible to click on it", 15);  //нажатие на крестик в поиске
        waitForElementAndClick(By.xpath("//*[@class='android.widget.ImageButton']"), "The element was not found or it is impossible to click on it", 15); //нажатие на кнопку назад
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='Мои списки']/android.widget.ImageView"), "The element was not found or it is impossible to click on it", 15); //нажатие на кнопку списков для перехода к моим спискам
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Learning programming')]"), "The element was not found or it is impossible to click on it", 15); //нажатие на мой список
        swipeElementToLeft(By.xpath("//*[contains(@text, 'Java')]"), "Couldn't swipe left");  //удаление первой статьи в списке
        waitForElementPresent(By.xpath("//*[contains(@text, 'Java')]"), "The element was not found or it is impossible to click on it", 15); //смотрим что есть вторая
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Java')]"), "The element was not found or it is impossible to click on it", 15); //открыть вторую статью
        assertElementHasText(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[1][contains(@text, 'Java')]"), "JavaScript", "Text does not match"); //сравнить название статьи
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

    protected void swipeUp (int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);
        action.press(PointOption.point(x, start_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(timeOfSwipe))).moveTo(PointOption.point(x, end_y)).release().perform();

    }
    protected void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by, error_message, 15);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(30))).moveTo(PointOption.point(left_x, middle_y)).release().perform();
    }

    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }
    private void assertElementNotPresent(By by, String error_message){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements>0){
            String default_message = "An element '" + by.toString() + "'supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }

    }

    protected void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements == 0) {
            throw new AssertionError(error_message);
        }
    }




}
