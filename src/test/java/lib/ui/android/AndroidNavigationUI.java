package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
static {
    BOOKMARK_BTN = "id:org.wikipedia:id/article_menu_bookmark";
    BACK_BTN = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/*[1]";
    KEBAB_MENU = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu";
     EXPLORE_MENU = "id:org.wikipedia:id/overflow_feed";
     SKIP_BTN = "id:org.wikipedia:id/fragment_onboarding_skip_button";
}
    public AndroidNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
