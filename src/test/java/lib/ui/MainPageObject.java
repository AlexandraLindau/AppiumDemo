package lib.ui;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.function.Function;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
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
                return AppiumBy.accessibilityId(locator);
            case "css":
                return By.cssSelector(locator);
            default:
                throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds) {

        WebElement element = null;
        By by = this.getLocatorByString(locator);

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        element = (WebElement) wait.until(new Function() {
            @Override
            public Object apply(Object o) {
                return driver.findElement(by);
            }
        });

        return element;
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
        int startY = (int) (dim.getHeight() * 0.40);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), x, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        System.out.println(dragNDrop.toJson().toString());
        driver.perform(Collections.singletonList(dragNDrop));
    }

    public void scrollUp() {
        Dimension dim = driver.manage().window().getSize();
        int x = dim.getWidth() / 2;
        int startY = (int) (dim.getHeight() * 0.15);
        int endY = (int) (dim.getHeight() * 0.40);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), x, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        System.out.println(dragNDrop.toJson().toString());
        driver.perform(Collections.singletonList(dragNDrop));
    }

    public void scrollToFindElement(boolean scrollDown, String locator, int maxSwipes) {
        int alreadySwiped = 0;
        while (!isElementLocatedOnScreen(locator)) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(locator, "Cannot find element by swiping down/up", 0);
                return;
            }
            if (scrollDown) {
                scrollDown();
            } else scrollUp();
            alreadySwiped++;
        }
    }

    public boolean isElementLocatedOnScreen(String locator) {
        boolean result = false;
        WebElement element = null;
        int retries = 5;
        for (int i = 0; i <= retries; i++) {
            try {
                element = waitForElementPresent(locator,
                        "Cannot find element by locator: " + locator, 10);
                int elementTopLeftX = element.getLocation().getX();
                int elementTopLeftY = element.getLocation().getY();
                int elementBottomRightX = elementTopLeftX + element.getSize().getWidth();
                int elementBottomRightY = elementTopLeftY + element.getSize().getHeight();

                int screenTopLeftX = 0;
                int screenTopLeftY = 0;
                int screenBottomRightX = driver.manage().window().getSize().getWidth();
                int screenBottomRightY = driver.manage().window().getSize().getHeight();

                System.out.println("Element top left X,Y: " + elementTopLeftX + ", " + elementTopLeftY);
                System.out.println("Element bottom right X,Y: " + elementBottomRightX + ", " + elementBottomRightY);
                System.out.println("Screen top left X,Y: " + screenTopLeftX + ", " + screenTopLeftY);
                System.out.println("Screen bottom right X,Y: " + screenBottomRightX + ", " + screenBottomRightY);

                result = elementTopLeftX >= screenTopLeftX && elementTopLeftY >= screenTopLeftY &&
                        elementBottomRightX <= screenBottomRightX && elementBottomRightY <= screenBottomRightY;
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException. Retrying...");
                if (i == retries) {
                    System.out.println("StaleElementReferenceException");
                    throw e;
                }
            } catch (TimeoutException e) {
                return false;
            }
        }
        return result;
    }
}
