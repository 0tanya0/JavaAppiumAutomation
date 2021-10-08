package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String searchText = "Java";
        searchPageObject.typeSearchText(searchText);
        searchPageObject.clickByArticleWithDescription("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String nameList = "Test";
        articlePageObject.addArticleToNewList(nameList);

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.goToHomeScreen();

        MyListPageObject myListPageObject = new MyListPageObject(driver);
        myListPageObject.clickSavedNavigationBar();
        myListPageObject.openSavedListByName(nameList);
        myListPageObject.removeArticleFromSavedListBySwipe(searchText);
    }
}
