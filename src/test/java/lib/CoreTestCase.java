package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import lib.ui.NavigationUI;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String appiumURL = "http://127.0.0.1:4723/wd/hub";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilityByPlatform();
        driver = new AndroidDriver(new URL(appiumURL), capabilities);
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickSkipButton();
    }

    protected void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void runAppInBackground() {
        driver.runAppInBackground(Duration.ofSeconds(2));
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getCapabilityByPlatform() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "C:\\Projects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
            capabilities.setCapability("orientation", "PORTRAIT");
        } else if (platform.equals(PLATFORM_IOS)) {
//            capabilities.setCapability("platformName", "ios");
//            capabilities.setCapability("deviceName", "iPhone SE");
//            capabilities.setCapability("platformVersion", "11.3");
//            capabilities.setCapability("app", "/Users...");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

}