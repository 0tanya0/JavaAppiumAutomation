package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch(){
        String searchText = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
    @Test
    public void testCancelSearch(){
        String searchText = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.assertAmountOfFoundArticle();
        searchPageObject.clickCancelSearchBtn();
        searchPageObject.waitForCancelBtnToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchText = "Linkin Park Discography";
        searchPageObject.typeSearchText(searchText);
        searchPageObject.assertAmountOfFoundArticle();
    }
    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchText = "gfdhjuiogdgfr";
        searchPageObject.typeSearchText(searchText);
        searchPageObject.assertThereIsNoResultOfSearch();
    }
    @Test
    public void testSearchByArticleAndDescription(){
        String searchText = "University of";
        String descriptionOfArticle = "university";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.waitForElementByTitleAndDescription(searchText,descriptionOfArticle);
        searchPageObject.assertAmountOfResultsByDescAndArticle(searchText,descriptionOfArticle);
    }
}
