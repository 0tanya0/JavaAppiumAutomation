import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Projects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        //Skip Welcome page
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Element Not found",
                5);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testTextVerification(){

        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "Element with Expected text NOT found"
        );

//        waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Element NOT found",
//                5
//        );
//        waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Java",
//                "Element NOT found",
//                5
//        );
//        waitForElementPresent(
//                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text,'Object-oriented programming language')]"),
//                "Cannot find 'Object-oriented programming language' topic searching be 'Java'",
//                15
//        );
    }

    @Test
    public void testCancelSearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Element NOT found",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_toolbar']/*[1]"),
                "Element NOT found",
                5
        );
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_toolbar']/*[1]"),
                "Element NOT found",
                5
        );
    }
    @Test
    public void firstTest(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Element NOT found",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Element NOT found",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language' topic searching be 'Java'",
                15
        );
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@content-desc='Java (programming language)'][@class='android.view.View']"),
                "error",
                5
        );
//        String article_class = title_element.getAttribute("class");
//        String article_title = title_element.getAttribute("content-desc");
//        Assert.assertEquals(
//                "blabla",
//                "Java (programming language)",
//                article_title
//        );

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by,error_message,5);
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String textExpected, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        String textActual = element.getText();
        Assert.assertEquals(error_message, textExpected, textActual);
    }
}
