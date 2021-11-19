package lib.ui.android;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {
    static {
        SAVED_NAVIGATION_BAR = "xpath://*[@content-desc='Saved']";
        RECYCLER_VIEW = "xpath://*[@resource-id='org.wikipedia:id/recycler_view']";
        SAVED_LIST_ARTICLES_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]";
        SAVED_LIST_DESCRIPTION_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_description'][contains(@text,'{DESCRIPTION}')]";
        DELETE_MY_LIST_OK_BTN = "xpath://*[@text='OK']";
        ADD_TO_LIST_ACTION = "xpath://*[@text='ADD TO LIST']";
        REMOVE_FROM_READING_LISTS_MENU = "xpath://*[@text='Remove from reading lists']";
        NAME_OF_FOLDER_CHECKLIST_TPL = "xpath://*[@text='{NAMELIST}']";
    }
    public AndroidMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
