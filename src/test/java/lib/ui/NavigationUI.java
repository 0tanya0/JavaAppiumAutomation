package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    public static final String
            BOOKMARK_BTN_BY_ID = "org.wikipedia:id/article_menu_bookmark",
            BACK_BTN_BY_XPATH = "//*[@resource-id='org.wikipedia:id/page_toolbar']/*[1]",
            KEBAB_MENU_BY_ID = "org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            EXPLORE_MENU_BY_ID = "org.wikipedia:id/overflow_feed",
            SKIP_BTN_BY_ID = "org.wikipedia:id/fragment_onboarding_skip_button";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickBookmarkBtn(){
        waitForElementAndClick(
                By.id(BOOKMARK_BTN_BY_ID),
                "Element BOOKMARK_BTN_BY_ID not found",
                5
        );
    }
    public void clickKebabBtn(){
        waitForElementAndClick(
                By.id(KEBAB_MENU_BY_ID),
                "Element KEBAB_MENU_BY_ID not found",
                15
        );
    }
    public void clickBackBtn(){
        waitForElementAndClick(
                By.xpath(BACK_BTN_BY_XPATH),
                "Element BACK_BTN_BY_XPATH not found",
                15
        );
    }
    public void clickExploreMenu(){
        waitForElementAndClick(
                By.id(EXPLORE_MENU_BY_ID),
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
                By.id(SKIP_BTN_BY_ID),
                "Skip btn is not present",
                5
        );
    }
}
