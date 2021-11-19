package lib.ui.android;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
static {
    BOOKMARK_BTN = "id:org.wikipedia:id/article_menu_bookmark";
    BACK_BTN = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar']/*[1]";
    KEBAB_MENU = "id:org.wikipedia:id/page_toolbar_button_show_overflow_menu";
     EXPLORE_MENU = "id:org.wikipedia:id/overflow_feed";
     SKIP_BTN = "id:org.wikipedia:id/fragment_onboarding_skip_button";
}
    public AndroidNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
