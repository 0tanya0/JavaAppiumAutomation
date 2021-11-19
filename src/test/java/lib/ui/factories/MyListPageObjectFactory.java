package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.ios.IOSMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {
    public static MyListPageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        } else {
            return new IOSMyListPageObject(driver) {
            };
        }
    }
}
