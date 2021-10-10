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

public class MainPageObject {
    protected AppiumDriver driver;
    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public void assertElementPresent(By by) {
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        Boolean isPresent = driver.findElements(by).size() > 0;
        Assert.assertTrue("Element not present", isPresent);
    }

    public void clickElementFromListByIndex(By by, int index, String errorMessage){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withMessage(errorMessage + "\n");
        List<WebElement> searchResultList = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        searchResultList.get(index).click();
    }

    public WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String errorMessage){
        return waitForElementPresent(by,errorMessage,5);
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void waitForElementAndClick(By by, String errorMessage, long timeOutInSecond){
        WebElement element = waitForElementPresent(by,errorMessage,timeOutInSecond);
        element.click();
    }

    public void waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,errorMessage,5);
        element.sendKeys(value);
    }
    public void waitForElementAndClear(By by, String errorMessage){
        WebElement element = waitForElementPresent(by,errorMessage,5);
        element.clear();
    }

    public void assertElementHasText(By by, String expectedText, String errorMessage){
        WebElement element = waitForElementPresent(by,errorMessage,5);
        String actualText = element.getText();
        Assert.assertTrue(errorMessage, actualText.contains(expectedText));
    }

    public void clickElementFromListHasTextByXpath(String xpath, String searchText, String errorMessage){
        String searchTextInXpath = xpath+"//*[@text='"+searchText+"']";
        waitForElementAndClick(By.xpath(searchTextInXpath),errorMessage,5);
    }

    public int getAmountsOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void swipeUp(int timeOfSwipe){
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action.press(PointOption.point(x,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x,endY))
                .release()
                .perform();
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void swipeUpToFindElement(By by, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0){
            if(alreadySwiped>maxSwipes){
                waitForElementPresent(by,"Cannot find element by swipping Up. \n"+errorMessage,0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void swipeElementToLeft(By by, String errorMessage){
        WebElement element = waitForElementPresent(
                by,
                "Element is NOT present",
                10
        );
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY)/2;
        TouchAction action = new TouchAction(driver);
        action
                .press(PointOption.point(rightX,middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)))
                .moveTo(PointOption.point(leftX,middleY))
                .release()
                .perform();
    }

    public void assertElementNotPresent(By by, String errorMessage){
        int amountsOfElements = getAmountsOfElements(by);
        if (amountsOfElements>0){
            String defaultMessage = "An element '"+by.toString()+"'is present";
            throw new AssertionError(defaultMessage+errorMessage);
        }
    }
    public String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutInSecond){
        WebElement element = waitForElementPresent(by, errorMessage,timeoutInSecond);
        return element.getAttribute(attribute);
    }


}

