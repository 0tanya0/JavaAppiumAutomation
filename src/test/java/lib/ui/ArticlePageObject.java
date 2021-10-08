package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE_OF_ARTICLE = "//*[@resource-id='pcs']/*[1]/*[1]",
            FOOTER_TEXT_BY_XPATH = "//*[@text='View article in browser']",
            ADD_TO_LIST_ACTION_BY_XPATH = "//*[@text='ADD TO LIST']",
            MY_LIST_NAME_INPUT_BY_ID = "org.wikipedia:id/text_input",
            MY_LIST_OK_BTN_BY_ID = "android:id/button1";


    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public void assertTitleOfArticle(String expectedText){
        Assert.assertTrue("Title of article doesn't contain text",
                getArticleTitle().contains(expectedText));
    }

    public WebElement waitForTitleElement(){
        return waitForElementPresent(
                By.xpath(TITLE_OF_ARTICLE),
                "Element TITLE_OF_ARTICLE not found"
        );
    }
    public void swipeToFooter(){
        swipeUpToFindElement(
                By.id(FOOTER_TEXT_BY_XPATH),
                "Cannot find the end of article",
                20
        );
    }
    public void addArticleToNewList(String nameList){
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickBookmarkBtn();

        waitForElementAndClick(
                By.xpath(ADD_TO_LIST_ACTION_BY_XPATH),
                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
                5
        );
        waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT_BY_ID),
                nameList,
                "Element MY_LIST_NAME_INPUT_BY_ID not found",
                5
        );
        waitForElementAndClick(
                By.id(MY_LIST_OK_BTN_BY_ID),
                "Element MY_LIST_OK_BTN_BY_ID not found",
                5
        );

    }
    public String getArticleTitle(){
        WebElement element = waitForTitleElement();
        return element.getAttribute("text");
    }
}
