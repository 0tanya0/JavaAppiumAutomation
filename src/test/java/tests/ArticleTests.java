package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle(){
        String searchText = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.clickByArticleWithDescription("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertTitleOfArticle(searchText);
    }
    @Test
    public void testSwipeArticle(){
        String searchText = "Appium";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.clickElementByTitle(searchText);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.assertTitleOfArticle(searchText);
        articlePageObject.swipeToFooter();
    }
}
