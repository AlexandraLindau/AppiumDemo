package lib.ui.pageObjects;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPageObject extends MainPageObject {

    public ProductDetailsPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            SCREEN_HEADER,
            PRODUCT_PRICE,
            ITEMS_NAMES,
            ITEMS_PRICES,
            AMOUNT_COUNTER,
            COUNTER_PLUS_BUTTON,
            COUNTER_MINUS_BUTTON,
            ADD_TO_CART_BUTTON,
            PRODUCT_DESCRIPTION;

    public String getProductName() {
        WebElement element = waitForElementPresent(SCREEN_HEADER, "Cannot find screen header", 10);
        return element.getText();
    }

    public void waitForHeaderElement() {
        this.waitForElementPresent(SCREEN_HEADER, "Screen header is not visible", 60);
    }

}

