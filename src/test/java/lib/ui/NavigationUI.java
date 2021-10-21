package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    public static final String
            BOOKMARK_BTN = "id:org.wikipedia:id/article_menu_bookmark",
            BACK_BTN = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/*[1]",
            KEBAB_MENU = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            EXPLORE_MENU = "id:org.wikipedia:id/overflow_feed",
            SKIP_BTN = "id:org.wikipedia:id/fragment_onboarding_skip_button";

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
