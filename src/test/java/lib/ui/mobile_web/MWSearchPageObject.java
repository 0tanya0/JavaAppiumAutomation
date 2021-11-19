package lib.ui.mobile_web;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "id:searchIcon";
        SEARCH_FIELD_WIKIPEDIA = "xpath://*[@class='search mw-ui-background-icon-search']";
        SEARCH_TITLE_TPL = "xpath://*[contains(@title,'{SUBSTRING}')]";
        SEARCH_CANCEL_BTN = "xpath://div[@class='header-action']";
        SEARCH_RESULT_DESCRIPTION_OF_ARTICLE_TPL = "xpath://div[contains(text(),'{DesOfArticle}')]";
        SEARCH_RESULT = "xpath://li[@class='page-summary']/*";
        SEARCH_RESULT_EMPTY = "xpath://*[@resource-id='org.wikipedia:id/results_text'][@text='No results']";
        SEARCH_EMPTY_CONTAINER= "id:org.wikipedia:id/search_empty_container";
        SEARCH_RESULT_DESCRIPTION_AND_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{TITLE}')]/following-sibling::*[@resource-id='org.wikipedia:id/page_list_item_description'][contains(@text,'{DESCRIPTION}')]/..";
    }
    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
