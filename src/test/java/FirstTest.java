import io.appium.java_client.AppiumDriver;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    private AppiumDriver driver;

    //*********Locators*********
    private static final String SEARCH_FIELD_WIKIPEDIA_BY_XPATH = "//*[contains(@text,'Search Wikipedia')]";
    private static final String SKIP_BTN_BY_ID = "org.wikipedia:id/fragment_onboarding_skip_button";
    private static final String SEARCH_SRC_TEXT_BY_ID = "org.wikipedia:id/search_src_text";
    private static final String SEARCH_RES_LIST_BY_XPATH = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']";
    private static final String SEARCH_CLOSE_BTN_BY_ID = "org.wikipedia:id/search_close_btn";
    private static final String SEARCH_EMPTY_CONTAINER_BY_ID= "org.wikipedia:id/search_empty_container";
    private static final String FOOTER_TEXT_BY_XPATH = "//*[@text='View article in browser']";
    private static final String BOOKMARK_BTN_BY_ID = "org.wikipedia:id/article_menu_bookmark";
    private static final String ADD_TO_LIST_ACTION_BY_XPATH = "//*[@text='ADD TO LIST']";
    private static final String NAME_OF_THE_LIST_BY_ID = "org.wikipedia:id/text_input";
    private static final String CREATING_LIST_OK_BTN_BY_ID = "android:id/button1";
    private static final String BACK_BTN_BY_XPATH = "//*[@resource-id='org.wikipedia:id/page_toolbar']/*[1]";
    private static final String KEBAB_MENU_BY_ID = "org.wikipedia:id/page_toolbar_button_show_overflow_menu";
    private static final String EXPLORE_MENU_BY_ID = "org.wikipedia:id/overflow_feed";
    private static final String SAVED_NAVIGATION_BAR_BY_XPATH = "//*[@content-desc='Saved']";
    private static final String RECYCLER_VIEW_BY_XPATH = "//*[@resource-id='org.wikipedia:id/recycler_view']";
    private static final String LIST_SAVED_ARTICLES_BY_XPATH = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private static final String TITLE_OF_ARTICLE_BY_XPATH = "//*[@resource-id='pcs']";

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
                "Element 'Skip button' Not found",
                5
        );
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testAssertTitle(){
        String text = "Java";

        searchArticleByText(text);
        clickElementFromListByIndex(
                By.xpath(SEARCH_RES_LIST_BY_XPATH),
                0,
                "Element SEARCH_RES_LIST with"+text+" not found"
        );
        assertElementPresent(
                By.xpath(TITLE_OF_ARTICLE_BY_XPATH+"//*[contains(@text,'"+ text +"')]")
        );
    }

        private void assertElementPresent(By by) {
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            Boolean isPresent = driver.findElements(by).size() > 0;
            Assert.assertTrue("Element not present", isPresent);
        }

    private void assertTitleOfArticle(String text) {
        assertElementHasText(
                By.xpath(TITLE_OF_ARTICLE_BY_XPATH+"//*[contains(@text,'"+ text +"')]"),
                text,
                "Element TITLE_OF_ARTICLE_BY_XPATH with"+ text +" not found"
        );
    }

    private void removeArticleFromList(String nameList) {
        clickBookmarkBtn();
        waitForElementAndClick(
                By.xpath("//*[@text='Remove from reading lists']"),
                "Element TITLE_REMOVE_FROM_SAVED_BY_XPATH not found",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='"+ nameList +"']"),
                "Element "+ nameList +" not found",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Element "+ nameList +" not found",
                5
        );
    }

    private void selectArticleFromSavedList(String nameList, String text) {
        clickSavedNavigationBar();
        clickElementFromListHasTextByXpath(
                RECYCLER_VIEW_BY_XPATH,
                nameList,
                "Element SAVED_ARTICLE_LIST_BY_XPATH not found"
        );
        waitForElementAndClick(
                By.xpath(LIST_SAVED_ARTICLES_BY_XPATH+"[contains(@text,'"+text+"')]"),
                "Element SAVED_ARTICLE_LIST_BY_XPATH not found",
                5
        );
    }

    private void goToHomeScreen() {
        clickKebabBtn();
        clickExploreMenu();
    }

    private void clickAndSearchArticleByText(String text) {
        searchArticleByText(text);
        clickArticleByText(text, 0);
    }

    private void clickExploreMenu(){
        waitForElementAndClick(
                By.id(EXPLORE_MENU_BY_ID),
                "Element EXPLORE_MENU_BY_ID not found",
                5
        );
    }

    private void clickSavedNavigationBar(){
        waitForElementAndClick(
                By.xpath(SAVED_NAVIGATION_BAR_BY_XPATH),
                "Element SAVED_NAVIGATION_BAR_BY_XPATH not found",
                5
        );
        waitForElementPresent(
                By.xpath(RECYCLER_VIEW_BY_XPATH),
                "Element RECYCLER_VIEW_BY_XPATH not found"
        );
    }
    private void clickKebabBtn(){
        waitForElementAndClick(
                By.id(KEBAB_MENU_BY_ID),
                "Element KEBAB_MENU_BY_ID not found",
                15
        );
    }
    private void clickBackBtn(){
        waitForElementAndClick(
                By.xpath(BACK_BTN_BY_XPATH),
                "Element BACK_BTN_BY_XPATH not found",
                15
        );
    }
    private void addArticleToSavedList(String nameList){
        clickBookmarkBtn();
        waitForElementAndClick(
                By.xpath(ADD_TO_LIST_ACTION_BY_XPATH),
                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='"+nameList+"']"),
                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
                5
        );
    }
    private void createNewList(String nameList){
        clickBookmarkBtn();
        waitForElementAndClick(
                By.xpath(ADD_TO_LIST_ACTION_BY_XPATH),
                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
                5
        );
        waitForElementAndSendKeys(
                By.id(NAME_OF_THE_LIST_BY_ID),
                nameList,
                "Element NAME_OF_THE_LIST_BY_ID not found",
                5
        );
        waitForElementAndClick(
                By.id(CREATING_LIST_OK_BTN_BY_ID),
                "Element CREATING_LIST_OK_BTN_BY_ID not found",
                5
        );

    }
    private void clickBookmarkBtn(){
        waitForElementAndClick(
                By.id(BOOKMARK_BTN_BY_ID),
                "Element BOOKMARK_BTN_BY_ID not found",
                5
        );
    }
    private void searchArticleByText(String text){
        waitForElementAndClick(
                By.xpath(SEARCH_FIELD_WIKIPEDIA_BY_XPATH),
                "Element SEARCH_FIELD_WIKIPEDIA not found",
                5
        );
        waitForElementAndSendKeys(
                By.id(SEARCH_SRC_TEXT_BY_ID),
                text,
                "Element with '"+text+"' not found",
                5
        );
        waitForElementPresent(
                //search for child with index 3
                By.xpath(SEARCH_RES_LIST_BY_XPATH+"//..[@index='3']"),
                "Elements with "+text+" not found",
                15
        );
    }

    private void clickArticleByText(String text, int index){
        clickElementFromListByIndex(
                By.xpath(SEARCH_RES_LIST_BY_XPATH),
                index,
                "Element SEARCH_RES_LIST with"+text+" not found"
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='pcs']//*[contains(@text,'"+text+"')]"),
                "Element SEARCH_RES_LIST with"+text+" not found"
        );
    }

    private void clickElementFromListByIndex(By by, int index, String errorMessage){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        List <WebElement> searchResultList = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        searchResultList.get(index).click();
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage){
        return waitForElementPresent(by,errorMessage,5);
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private void waitForElementAndClick(By by, String errorMessage, long timeOutInSecond){
        WebElement element = waitForElementPresent(by,errorMessage,timeOutInSecond);
        element.click();
    }

    private void waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,errorMessage,5);
        element.sendKeys(value);
    }
    private void waitForElementAndClear(By by, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,errorMessage,5);
        element.clear();
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage){
        WebElement element = waitForElementPresent(by,errorMessage,5);
        String actualText = element.getText();
        Assert.assertTrue(errorMessage, actualText.contains(expectedText));
    }

    private void assertListHasTextByXpath(String xpath, String searchText, String errorMessage){
        String toLowerCaseValue = searchText.toLowerCase();
        String searchTextInXpath = xpath+"//*[contains(lower-case(@text),'"+toLowerCaseValue+"')]";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        List <WebElement> searchResultList = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(searchTextInXpath)));
        Assert.assertTrue("Text"+searchText+" not found in the list",searchResultList.size()>3);
    }

    private void clickElementFromListHasTextByXpath(String xpath, String searchText, String errorMessage){
        String searchTextInXpath = xpath+"//*[@text='"+searchText+"']";
        waitForElementAndClick(By.xpath(searchTextInXpath),errorMessage,5);
    }

    private void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(PointOption.point(x,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x,endY))
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    private void swipeUpToFindElement(By by, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0){
            if(alreadySwiped>maxSwipes){
                waitForElementPresent(by,"Cannot find element by swipping Up. \n"+errorMessage,0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }
}
