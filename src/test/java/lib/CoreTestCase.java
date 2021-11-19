package lib;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.NavigationUI;
import lib.ui.factories.NavigationUIFactory;
import org.junit.Ignore;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

@Ignore
public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        welcomePage();
        openWikiWebPageForMobileWeb();
    }

    protected void welcomePage(){
        if(driver instanceof AppiumDriver){
        AppiumDriver driver = (AppiumDriver) this.driver;
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickSkipButton();
        } else {
            System.out.println("Method welcomePage does nothing for platform ");
        }
    }

    protected void rotateScreenPortrait() {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform ");
        }
    }

    protected void rotateScreenLandscape() {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform ");
        }
    }

    protected void runAppInBackground() {
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(2));
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform ");
        }
    }

    protected void openWikiWebPageForMobileWeb(){
        if(Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for platform");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}