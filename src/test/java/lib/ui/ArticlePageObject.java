package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_OF_ARTICLE = "xpath://*[@resource-id='pcs']/*[1]/*[1]",
            FOOTER_TEXT = "xpath://*[@text='View article in browser']",
            ADD_TO_LIST_ACTION = "xpath://*[@text='ADD TO LIST']",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BTN = "id:android:id/button1";


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
        NavigationUI navigationUI = new NavigationUI(driver);
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
        return element.getAttribute("text");
    }
    public void assertElementPresentWithoutWait(String searchText){
        assertElementPresent(
                TITLE_OF_ARTICLE+"[contains(@text,'"+ searchText +"')]"
        );
    }

}
