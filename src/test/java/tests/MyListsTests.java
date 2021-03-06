package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String searchText = "Java";
        searchPageObject.typeSearchText(searchText);
        String descriptionOfArticle ="Object-oriented programming language";
        searchPageObject.clickByArticleWithDescription(descriptionOfArticle);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String nameList = "Test";
        articlePageObject.addArticleToNewList(nameList);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.goToHomeScreen();
        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
        myListPageObject.clickSavedNavigationBar();
        myListPageObject.openSavedListByName(nameList);
        myListPageObject.removeArticleByDescriptionFromSavedList(descriptionOfArticle);
    }
    @Test
    public void testSavedTwoArticles(){
            String nameList = "Test";
            String searchText = "Java";
            String descriptionOfArticle1 = "Object-oriented programming language";
            String descriptionOfArticle2 = "Indonesian island";

            SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchText(searchText);
            searchPageObject.clickByArticleWithDescription(descriptionOfArticle1);
            ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
            articlePageObject.waitForTitleElement();
            articlePageObject.addArticleToNewList(nameList);
            NavigationUI navigationUI = NavigationUIFactory.get(driver);
            navigationUI.clickBackBtn();
            searchPageObject.clickByArticleWithDescription(descriptionOfArticle2);
            MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);
            myListPageObject.addArticleToSavedList(nameList);
            //step 2
            navigationUI.goToHomeScreen();
            myListPageObject.clickSavedNavigationBar();
            myListPageObject.openSavedListByName(nameList);
            myListPageObject.removeArticleByDescriptionFromSavedList(descriptionOfArticle1);
            //step 3
            myListPageObject.clickArticleFromSavedListByText(searchText);
            //step 4
            articlePageObject.assertTitleOfArticle(searchText);
    }
}
