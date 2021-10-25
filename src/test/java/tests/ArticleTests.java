package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle(){
        String searchText = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.clickByArticleWithDescription("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertTitleOfArticle(searchText);
    }
    @Test
    public void testSwipeArticle(){
        String searchText = "Appium";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.clickElementByTitle(searchText);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.assertTitleOfArticle(searchText);
        articlePageObject.swipeToFooter();
    }

    public void testAssertTitle(){
        String searchText = "Java";
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        searchPageObject.clickElementByTitle(searchText);
        articlePageObject.assertElementPresentWithoutWait(searchText);
    }

}
