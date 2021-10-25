package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE_OF_ARTICLE = "xpath://*[@resource-id='pcs']/*[1]/*[1]";
        FOOTER_TEXT = "xpath://*[@text='View article in browser']";
        ADD_TO_LIST_ACTION = "xpath://*[@text='ADD TO LIST']";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BTN = "id:android:id/button1";
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
