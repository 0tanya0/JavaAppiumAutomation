package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.ui.factories.NavigationUIFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject{
    protected static String
            SAVED_NAVIGATION_BAR,
            RECYCLER_VIEW,
            SAVED_LIST_ARTICLES_TPL,
            SAVED_LIST_DESCRIPTION_TPL,
            DELETE_MY_LIST_OK_BTN,
            ADD_TO_LIST_ACTION,
            REMOVE_FROM_READING_LISTS_MENU,
            NAME_OF_FOLDER_CHECKLIST_TPL;

    public MyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
    //Templates methods
    private static String getTitleArticleFromSavedList(String titleOfArticle){
        return SAVED_LIST_ARTICLES_TPL.replace("{TITLE}",titleOfArticle);
    }
    private static String getDescriptionOfArticleFromSavedList(String descriptionOfArticle){
        return SAVED_LIST_DESCRIPTION_TPL.replace("{DESCRIPTION}",descriptionOfArticle);
    }
    private static String getNameFolder(String nameOfList){
        return NAME_OF_FOLDER_CHECKLIST_TPL.replace("{NAMELIST}",nameOfList);
    }
    public void clickSavedNavigationBar(){
        this.waitForElementAndClick(
                SAVED_NAVIGATION_BAR,
                "Element SAVED_NAVIGATION_BAR_BY_XPATH not found",
                5
        );
        this.waitForElementPresent(
                RECYCLER_VIEW,
                "Element RECYCLER_VIEW not found"
        );
    }
    public void clickArticleFromSavedListByText(String text){
        String searchArticle = getTitleArticleFromSavedList(text);
        waitForElementAndClick(
                searchArticle,
                "Element SAVED_LIST_ARTICLES_TPL not found",
                5
        );
    }
    public void removeArticleFromSavedListBySwipe(String titleOfArticle){
        String searchArticle = getTitleArticleFromSavedList(titleOfArticle);
        swipeElementToLeft(
                searchArticle,
                "Element SAVED_LIST_ARTICLES_TPL not found"
        );
    }
    public void removeArticleByDescriptionFromSavedList(String descriptionOfArticle){
        String searchDescriptionOfArticle = getDescriptionOfArticleFromSavedList(descriptionOfArticle);
        swipeElementToLeft(
                searchDescriptionOfArticle,
                "Element SAVED_LIST_DESCRIPTION_TPL not found"
        );
        waitForElementNotPresent(
                searchDescriptionOfArticle,
                "Element SAVED_LIST_DESCRIPTION_TPL still present",
                10
        );
    }
    public void openSavedListByName(String nameList){
        clickElementFromListHasText(
                RECYCLER_VIEW,
                nameList,
                "Element SAVED_ARTICLE_LIST not found"
        );
    }
    //for article which saved into two lists
    public void removeArticleFromLists(String nameList) {
        waitForElementAndClick(
                REMOVE_FROM_READING_LISTS_MENU,
                "Element REMOVE_FROM_READING_LIST_MENU not found",
                5
        );
        String searchArticle = getNameFolder(nameList);
        waitForElementAndClick(
                searchArticle,
                "Element "+ nameList +" not found",
                5
        );
        waitForElementAndClick(
                DELETE_MY_LIST_OK_BTN,
                "Element "+ nameList +" not found",
                5
        );
    }
    public void addArticleToSavedList(String nameList){
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickBookmarkBtn();
        waitForElementAndClick(
                ADD_TO_LIST_ACTION,
                "Element ADD_TO_LIST_ACTION not found",
                5
        );
        String searchArticle = getNameFolder(nameList);
        waitForElementAndClick(
                searchArticle,
                "Element ADD_TO_LIST_ACTION not found",
                5
        );
    }
    public void clickElementFromListHasText(String xpath, String searchText, String errorMessage) {
        String locator = xpath + "//*[@text='" + searchText + "']";
        waitForElementAndClick(locator, errorMessage, 5);
    }
}
