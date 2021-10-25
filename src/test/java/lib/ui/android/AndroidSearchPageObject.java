package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_FIELD_WIKIPEDIA = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
        SEARCH_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_DESCRIPTION_OF_ARTICLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DesOfArticle}']";
        SEARCH_RESULT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*";
        SEARCH_RESULT_EMPTY = "xpath://*[@resource-id='org.wikipedia:id/results_text'][@text='No results']";
        SEARCH_EMPTY_CONTAINER= "id:org.wikipedia:id/search_empty_container";
        SEARCH_RESULT_DESCRIPTION_AND_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]/following-sibling::*[@resource-id='org.wikipedia:id/page_list_item_description'][contains(@text,'{DESCRIPTION}')]/..";
    }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
