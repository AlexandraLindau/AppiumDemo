package lib.ui.pagesIOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.pageObjects.ProductDetailsPageObject;

public class IOSProductDetailsPage extends ProductDetailsPageObject {

    public IOSProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    static {
        SCREEN_HEADER = "accessibility:container header";
        PRODUCT_PRICE = "accessibility:product price";
        AMOUNT_COUNTER = "xpath://XCUIElementTypeOther[@name='counter amount']/XCUIElementTypeStaticText";
        COUNTER_PLUS_BUTTON = "accessibility:counter plus button";
        COUNTER_MINUS_BUTTON = "accessibility:counter minus button";
        ADD_TO_CART_BUTTON = "accessibility:Add To Cart button";
        PRODUCT_DESCRIPTION = "accessibility:product description";
    }
}
