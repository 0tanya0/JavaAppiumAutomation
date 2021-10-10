package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch(){
        String searchText = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch(){
        String searchText = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.assertAmountOfFoundArticle();
        searchPageObject.clickCancelSearchBtn();
        searchPageObject.waitForCancelBtnToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchText = "Linkin Park Discography";
        searchPageObject.typeSearchText(searchText);
        searchPageObject.assertAmountOfFoundArticle();
    }
    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchText = "gfdhjuiogdgfr";
        searchPageObject.typeSearchText(searchText);
        searchPageObject.assertThereIsNoResultOfSearch();
    }
}
