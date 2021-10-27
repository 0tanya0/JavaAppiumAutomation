package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.NavigationUIFactory;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE_OF_ARTICLE,
            FOOTER_TEXT,
            ADD_TO_LIST_ACTION,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BTN;


    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public void assertTitleOfArticle(String expectedText){
        Assert.assertTrue("Title of article doesn't contain text",
                getArticleTitle().contains(expectedText));
    }
    public WebElement waitForTitleElement(){
        return waitForElementPresent(
                TITLE_OF_ARTICLE,
                "Element TITLE_OF_ARTICLE not found"
        );
    }
    public void swipeToFooter(){
        swipeUpToFindElement(
                FOOTER_TEXT,
                "Cannot find the end of article",
                10
        );
    }
    public void addArticleToNewList(String nameList){
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickBookmarkBtn();

        waitForElementAndClick(
                ADD_TO_LIST_ACTION,
                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
                5
        );
        waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                nameList,
                "Element MY_LIST_NAME_INPUT_BY_ID not found",
                5
        );
        waitForElementAndClick(
                MY_LIST_OK_BTN,
                "Element MY_LIST_OK_BTN_BY_ID not found",
                5
        );

    }
    public String getArticleTitle(){
        WebElement element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return element.getAttribute("text");
        } else {
            //here code for ios
            return element.getAttribute("name");
        }
    }
    public void assertElementPresentWithoutWait(String searchText){
        assertElementPresent(
                TITLE_OF_ARTICLE+"[contains(@text,'"+ searchText +"')]"
        );
    }

}
