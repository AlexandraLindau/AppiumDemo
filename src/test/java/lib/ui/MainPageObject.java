package lib.ui;

import io.appium.java_client.MobileBy;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class MainPageObject {

    protected WebDriver driver;

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }

    protected By getLocatorByString(String locator_with_type) {
        String[] exploaded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploaded_locator[0];
        String locator = exploaded_locator[1];

        switch (by_type) {
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "accessibility":
                return MobileBy.AccessibilityId(locator);
            case "css":
                return By.cssSelector(locator);
            default:
                throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {

        By by = this.getLocatorByString(locator);

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Trying to find element: " + locator);
                driver.findElement(by);
                System.out.println("Element found: " + locator);
                break;
            } catch (Exception e) {
                continue;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            System.out.println("ElementNotInteractableException: " + locator);
        }
        System.out.println("Clicked: " + locator);
        return element;
    }

    public void scrollDown() {
        Dimension dim = driver.manage().window().getSize();
        int x = dim.getWidth() / 2;
        int endY = (int) (dim.getHeight() * 0.15);
        int startY = (int) (dim.getHeight() * 0.25);

        TouchAction t = new TouchAction((PerformsTouchActions) driver);

        t.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).
                moveTo(PointOption.point(x, endY)).release().perform();
    }

    public void scrollUp() {
        Dimension dim = driver.manage().window().getSize();
        int x = dim.getWidth() / 2;
        int startY = (int) (dim.getHeight() * 0.15);
        int endY = (int) (dim.getHeight() * 0.25);

        TouchAction t = new TouchAction((PerformsTouchActions) driver);

        t.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).
                moveTo(PointOption.point(x, endY)).release().perform();
    }
}
