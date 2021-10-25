package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

abstract public class SearchPageObject extends MainPageObject{
    protected static String
            SEARCH_FIELD_WIKIPEDIA,
            SEARCH_INPUT,
            SEARCH_TITLE_TPL,
            SEARCH_CANCEL_BTN,
            SEARCH_RESULT_DESCRIPTION_OF_ARTICLE_TPL,
            SEARCH_RESULT,
            SEARCH_RESULT_EMPTY,
            SEARCH_EMPTY_CONTAINER,
            SEARCH_RESULT_DESCRIPTION_AND_TITLE;

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    //Templates methods
    private static String getSearchResultDescriptionOfArticle(String descOfArticle){
        return SEARCH_RESULT_DESCRIPTION_OF_ARTICLE_TPL.replace("{DesOfArticle}",descOfArticle);
    }
    private static String getTitleSearchElement(String subString){
        return SEARCH_TITLE_TPL.replace("{SUBSTRING}",subString);
    }
    public static String getSearchResultDescriptionAndTitle(String descOfArticle, String titleOfArticle){
        return SEARCH_RESULT_DESCRIPTION_AND_TITLE
                .replace("{TITLE}",titleOfArticle)
                .replace("{DESCRIPTION}",descOfArticle);
    }
    public void clickCancelSearchBtn(){
        waitForElementAndClick(
                SEARCH_CANCEL_BTN,
                "Cannot find SEARCH_CANCEL_BTN",
                5);
    }
    public void waitForCancelBtnToDisappear(){
        waitForElementNotPresent(
                SEARCH_CANCEL_BTN,
                "SEARCH_CANCEL_BTN is still present",
                5
        );
    }
    public void initSearchInput(){
        waitForElementAndClick(
                SEARCH_FIELD_WIKIPEDIA,
                "Element SEARCH_FIELD_WIKIPEDIA not found",
                5
        );
    }
    public void typeSearchText(String text){
        waitForElementAndSendKeys(
                SEARCH_INPUT,
                text,
                "Element with '"+text+"' not found",
                5
        );
    }
    public void clickElementByTitle(String titleOfArticle){
        String locator = getTitleSearchElement(titleOfArticle);
        waitForElementAndClick(
                locator,
                "Element with "+titleOfArticle+"not present",
                5
        );
    }
    public void assertTitleOfArticle(String subString) {
        String locator = getTitleSearchElement(subString);
        assertElementHasText(
                locator,
                subString,
                "Element SEARCH_TITLE_BY_ID_TPL with"+ subString +" not found"
        );
    }
    public void waitForSearchResult(String descriptionOfArticle){
        String locator = getSearchResultDescriptionOfArticle(descriptionOfArticle);
        waitForElementPresent(
                locator,
                "Element with"+ descriptionOfArticle +" not found"
        );
    }

    public void clickByArticleWithDescription(String descriptionOfArticle){
        String locator = getSearchResultDescriptionOfArticle(descriptionOfArticle);
        waitForElementAndClick(
                locator,
                "Element TITLE_OF_ARTICLE_BY_XPATH with "+ descriptionOfArticle +" not found",
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
                SEARCH_RESULT,
                "Search result list is not present"
        );
       return getAmountsOfElements(SEARCH_RESULT);
    }
    public void assertAmountOfFoundArticle(){
        int amountOfArticle = getAmountOfFoundArticle();
        Assert.assertTrue("Search result list is empty",amountOfArticle>1);
    }
    public void waitForEmptyResultsLabel(){
        waitForElementPresent(
                SEARCH_EMPTY_CONTAINER,
                "Result list is Not empty",
                15
        );
    }
    public void assertThereIsNoResultOfSearch(){
        waitForElementPresent(SEARCH_RESULT_EMPTY,"Search result list is NOT empty");
    }

    public void waitForElementByTitleAndDescription(String titleOfArticle, String descriptionOfArticle) {
        String locator = getSearchResultDescriptionAndTitle(descriptionOfArticle,titleOfArticle);
        waitForElementPresent(
                locator,
                "Element with "+ descriptionOfArticle +" not found"
        );
    }

    public void assertAmountOfResultsByDescAndArticle(String searchText, String descriptionOfArticle) {
        String locator = getSearchResultDescriptionAndTitle(descriptionOfArticle,searchText);
        int amountsOfResults = getAmountsOfElements(locator);
        Assert.assertTrue("Search result list is empty",amountsOfResults>2);
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
