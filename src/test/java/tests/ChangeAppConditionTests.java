package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults(){
        String searchText = "Java";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.clickByArticleWithDescription("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Article was changed after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
        rotateScreenPortrait();
        String titleAfterSecondRotation = articlePageObject.getArticleTitle();
        assertEquals(
                "Article was changed after second rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground(){
        String searchText = "Java";
        String expectedDescriptionOfArticle = "Object-oriented programming language";
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchText(searchText);
        searchPageObject.waitForSearchResult(expectedDescriptionOfArticle);
        runAppInBackground();
        searchPageObject.waitForSearchResult(expectedDescriptionOfArticle);

    }
}
