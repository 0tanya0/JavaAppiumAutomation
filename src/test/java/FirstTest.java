import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    //*********Locators*********
    private static final String SEARCH_FIELD_WIKIPEDIA_BY_XPATH = "//*[contains(@text,'Search Wikipedia')]";
    private static final String SKIP_BTN_BY_ID = "org.wikipedia:id/fragment_onboarding_skip_button";
    private static final String SEARCH_SRC_TEXT_BY_XPATH = "org.wikipedia:id/search_src_text";
    private static final String SEARCH_RES_LIST_BY_XPATH = "//*[@resource-id='org.wikipedia:id/search_results_list']";
    private static final String SEARCH_CLOSE_BTN_BY_ID = "org.wikipedia:id/search_close_btn";
    private static final String SEARCH_EMPTY_CONTAINER_BY_ID= "org.wikipedia:id/search_empty_container";


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
                By.id(SKIP_BTN_BY_ID),
                "Element 'Skip button' Not found"
        );
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void testTextVerification(){

        String expectedText = "Search Wikipedia";

        assertElementHasText(
                By.xpath(SEARCH_FIELD_WIKIPEDIA_BY_XPATH),
                expectedText,
                "Element with '" + expectedText + "' text NOT found"
        );
    }

    @Test
    public void testCancelSearch(){
        waitForElementAndClick(
                By.xpath(SEARCH_FIELD_WIKIPEDIA_BY_XPATH),
                "Element SEARCH_FIELD_WIKIPEDIA not found"
        );
        waitForElementAndSendKeys(
                By.id(SEARCH_SRC_TEXT_BY_XPATH),
                "Java",
                "Element SEARCH_SRC_TEXT not found",
                5
        );
        waitForElementPresent(
                //search for child with index 3
                By.xpath(SEARCH_RES_LIST_BY_XPATH+"//..[@index='3']"),
                "Elements with searching text not found",
                15
        );
        waitForElementAndClick(
                By.id(SEARCH_CLOSE_BTN_BY_ID),
                "Element SEARCH_CLOSE_BTN not found"
        );
        waitForElementPresent(
                By.id(SEARCH_EMPTY_CONTAINER_BY_ID),
                "Searching elements are NOT disappeared",
                15
        );
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

    private void waitForElementAndClick(By by, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.click();
    }

    private void waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.sendKeys(value);
    }
    private void waitForElementAndClear(By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.clear();
    }

    private void assertElementHasText(By by, String expectedText, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        String actualText = element.getText();
        Assert.assertEquals(error_message, expectedText, actualText);
    }
}
