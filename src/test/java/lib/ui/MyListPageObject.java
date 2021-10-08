package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{
    private static final String
            SAVED_NAVIGATION_BAR_BY_XPATH = "//*[@content-desc='Saved']",
            RECYCLER_VIEW_BY_XPATH = "//*[@resource-id='org.wikipedia:id/recycler_view']",
            SAVED_LIST_ARTICLES_BY_XPATH_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]",
            DELETE_MY_LIST_OK_BTN = "//*[@text='OK']",
            REMOVE_FROM_READING_LISTS_MENU = "//*[@text='Remove from reading lists']",
            NAME_OF_FOLDER_CHECKLIST_TPL = "//*[@text='{NAMELIST}']";

    public MyListPageObject(AppiumDriver driver){
        super(driver);
    }
    //Templates methods
    private static String getTitleArticleFromSavedList(String titleOfArticle){
        return SAVED_LIST_ARTICLES_BY_XPATH_TPL.replace("{TITLE}",titleOfArticle);
    }
    private static String getNameFolder(String nameOfList){
        return NAME_OF_FOLDER_CHECKLIST_TPL.replace("{NAMELIST}",nameOfList);
    }
    public void clickSavedNavigationBar(){
        this.waitForElementAndClick(
                By.xpath(SAVED_NAVIGATION_BAR_BY_XPATH),
                "Element SAVED_NAVIGATION_BAR_BY_XPATH not found",
                5
        );
        this.waitForElementPresent(
                By.xpath(RECYCLER_VIEW_BY_XPATH),
                "Element RECYCLER_VIEW_BY_XPATH not found"
        );
    }
    public void clickArticleFromSavedListByText(String text){
        String searchArticleXPath = getTitleArticleFromSavedList(text);
        waitForElementAndClick(
                By.xpath(searchArticleXPath),
                "Element SAVED_LIST_ARTICLES_BY_XPATH_TPL not found",
                5
        );
    }
    public void removeArticleFromSavedListBySwipe(String titleOfArticle){
        String searchArticleXPath = getTitleArticleFromSavedList(titleOfArticle);
        swipeElementToLeft(
                By.xpath(searchArticleXPath),
                "Element SAVED_LIST_ARTICLES_BY_XPATH_TPL not found",
                5
        );
        waitForElementNotPresent(
                By.xpath(searchArticleXPath),
                "Element SAVED_LIST_ARTICLES_BY_XPATH_TPL is still present",
                5
        );
    }
    public void openSavedListByName(String nameList){
        clickElementFromListHasTextByXpath(
                RECYCLER_VIEW_BY_XPATH,
                nameList,
                "Element SAVED_ARTICLE_LIST_BY_XPATH not found"
        );
    }
    //for article which saved into two lists
    public void removeArticleFromLists(String nameList) {
        waitForElementAndClick(
                By.xpath(REMOVE_FROM_READING_LISTS_MENU),
                "Element REMOVE_FROM_READING_LIST_MENU not found",
                5
        );
        String searchArticleXPath = getNameFolder(nameList);
        waitForElementAndClick(
                By.xpath(searchArticleXPath),
                "Element "+ nameList +" not found",
                5
        );
        waitForElementAndClick(
                By.xpath(DELETE_MY_LIST_OK_BTN),
                "Element "+ nameList +" not found",
                5
        );
    }
    public void addArticleToSavedList(String nameList){
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickBookmarkBtn();
//        waitForElementAndClick(
//                By.xpath(ADD_TO_LIST_ACTION_BY_XPATH),
//                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
//                5
//        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='"+nameList+"']"),
                "Element ADD_TO_LIST_ACTION_BY_XPATH not found",
                5
        );
    }
}
