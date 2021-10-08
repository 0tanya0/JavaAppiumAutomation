package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
            SEARCH_FIELD_WIKIPEDIA_BY_XPATH = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT_BY_ID = "org.wikipedia:id/search_src_text",
            SEARCH_TITLE_BY_ID_TPL = "org.wikipedia:id/page_list_item_title//*[contains(@text,'{SUBSTRING}')]",
            SEARCH_CANCEL_BTN_BY_ID = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_DESCRIPTION_OF_ARTICLE_BY_XPATH_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DesOfArticle}')]",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/search_results_list']",
            SEARCH_EMPTY_CONTAINER_BY_ID= "org.wikipedia:id/search_empty_container";


    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    //Templates methods
    private static String getSearchResultDescriptionOfArticle(String descOfArticle){
        return SEARCH_RESULT_DESCRIPTION_OF_ARTICLE_BY_XPATH_TPL.replace("{DesOfArticle}",descOfArticle);
    }
    private static String getTitleSearchElement(String subString){
        return SEARCH_TITLE_BY_ID_TPL.replace("{SUBSTRING}",subString);
    }

    public void clickCancelSearchBtn(){
        waitForElementAndClick(
                By.id(SEARCH_CANCEL_BTN_BY_ID),
                "Cannot find SEARCH_CANCEL_BTN",
                5);
    }
    public void waitForCancelBtnToDisappear(){
        waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BTN_BY_ID),
                "SEARCH_CANCEL_BTN is still present",
                5
        );
    }
    public void initSearchInput(){
        waitForElementAndClick(
                By.xpath(SEARCH_FIELD_WIKIPEDIA_BY_XPATH),
                "Element SEARCH_FIELD_WIKIPEDIA not found",
                5
        );
    }
    public void typeSearchText(String text){
        waitForElementAndSendKeys(
                By.id(SEARCH_INPUT_BY_ID),
                text,
                "Element with '"+text+"' not found",
                5
        );
    }
    public void assertTitleOfArticle(String subString) {
        String searchTitleXPath = getTitleSearchElement(subString);
        assertElementHasText(
                By.xpath(searchTitleXPath),
                subString,
                "Element SEARCH_TITLE_BY_ID_TPL with"+ subString +" not found"
        );
    }
    public void waitForSearchResult(String descriptionOfArticle){
        String searchResultXPath = getSearchResultDescriptionOfArticle(descriptionOfArticle);
        waitForElementPresent(
                By.xpath(searchResultXPath),
                "Element with"+ descriptionOfArticle +" not found"
        );
    }

    public void clickByArticleWithDescription(String descriptionOfArticle){
        String searchResultXPath = getSearchResultDescriptionOfArticle(descriptionOfArticle);
        waitForElementAndClick(
                By.xpath(searchResultXPath),
                "Element TITLE_OF_ARTICLE_BY_XPATH with"+ descriptionOfArticle +" not found",
                15
        );
    }
    public void searchArticleByText(String searchText, String descriptionOfArticle){
        initSearchInput();
        typeSearchText(searchText);
        waitForSearchResult(descriptionOfArticle);
    }

    public int getAmountOfFoundArticle(){
        waitForElementPresent(
                By.xpath(SEARCH_RESULT),
                "Search result list is not present"
        );
       return getAmountsOfElements(By.xpath(SEARCH_RESULT));
    }
    public void assertAmountOfFoundArticle(){
        int amountOfArticle = getAmountOfFoundArticle();
        Assert.assertTrue("Search result list is empty",amountOfArticle>0);
    }
    public void waitForEmptyResultsLabel(){
        waitForElementPresent(
                By.id(SEARCH_EMPTY_CONTAINER_BY_ID),
                "Result list is Not empty",
                15
        );
    }
    public void assertThereIsNoResultOfSearch(){
        assertElementNotPresent(By.xpath(SEARCH_RESULT),"Search result list is NOT empty");
    }

//    public void assertListHasTextByXpath(String xpath, String searchText, String errorMessage){
//        String toLowerCaseValue = searchText.toLowerCase();
//        String searchTextInXpath = xpath+"//*[contains(lower-case(@text),'"+toLowerCaseValue+"')]";
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.withMessage(errorMessage + "\n");
//        List<WebElement> searchResultList = wait.until(
//                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(searchTextInXpath)));
//        Assert.assertTrue("Text"+searchText+" not found in the list",searchResultList.size()>3);
//    }

}
