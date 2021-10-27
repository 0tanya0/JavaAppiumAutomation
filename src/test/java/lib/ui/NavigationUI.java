package lib.ui;
import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{
protected static String
            BOOKMARK_BTN,
            BACK_BTN,
            KEBAB_MENU,
            EXPLORE_MENU,
            SKIP_BTN;

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickBookmarkBtn(){
        waitForElementAndClick(
                BOOKMARK_BTN,
                "Element BOOKMARK_BTN not found",
                5
        );
    }
    public void clickKebabBtn(){
        waitForElementAndClick(
                KEBAB_MENU,
                "Element KEBAB_MENU not found",
                15
        );
    }
    public void clickBackBtn(){
        waitForElementAndClick(
                BACK_BTN,
                "Element BACK_BTN not found",
                15
        );
    }
    public void clickExploreMenu(){
        waitForElementAndClick(
                EXPLORE_MENU,
                "Element EXPLORE_MENU_BY_ID not found",
                5
        );
    }
    public void goToHomeScreen() {
        clickKebabBtn();
        clickExploreMenu();
    }
    public void clickSkipButton(){
        waitForElementAndClick(
                SKIP_BTN,
                "Skip btn is not present",
                5
        );
    }
}
