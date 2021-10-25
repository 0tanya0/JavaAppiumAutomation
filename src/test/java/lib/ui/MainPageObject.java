package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public void assertElementPresent(String locator) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        By by = this.getLocatorString(locator);
        Boolean isPresent = driver.findElements(by).size() > 0;
        Assert.assertTrue("Element not present", isPresent);
    }

    public void clickElementFromListByIndex(String locator, int index, String errorMessage) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        List<WebElement> searchResultList = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        searchResultList.get(index).click();
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSecond) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String errorMessage) {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSecond) {
        By by = this.getLocatorString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public void waitForElementAndClick(String locator, String errorMessage, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeOutInSecond);
        element.click();
    }

    public void waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSecond) {
        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        element.sendKeys(value);
    }

    public void waitForElementAndClear(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        element.clear();
    }

    public void assertElementHasText(String locator, String expectedText, String errorMessage) {
        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        String actualText = element.getText();
        Assert.assertTrue(errorMessage, actualText.contains(expectedText));
    }

    public int getAmountsOfElements(String locator) {
        By by = this.getLocatorString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes) {
        By by = this.getLocatorString(locator);
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swipping Up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        WebElement element = waitForElementPresent(
                locator,
                "Element is NOT present",
                10
        );
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;
        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(rightX, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(leftX, middleY))
                .release()
                .perform();
    }

    public void assertElementNotPresent(String locator, String errorMessage) {
        int amountsOfElements = getAmountsOfElements(locator);
        if (amountsOfElements > 0) {
            String defaultMessage = "An element '" + locator + "'is present";
            throw new AssertionError(defaultMessage + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSecond) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSecond);
        return element.getAttribute(attribute);
    }

    private By getLocatorString(String locatorWithType)
    {
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (byType.equals("xpath"))
        {
            return By.xpath(locator);
        } else if (byType.equals("id"))
        {
            return By.id(locator);
        } else
        {
            throw new IllegalArgumentException("Cannot get type locator: " + locatorWithType);
        }
    }
}

