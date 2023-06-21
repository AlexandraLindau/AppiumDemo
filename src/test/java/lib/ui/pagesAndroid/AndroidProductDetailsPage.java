package lib.ui.pagesAndroid;

import io.appium.java_client.AppiumDriver;
import lib.ui.pageObjects.ProductDetailsPageObject;

public class AndroidProductDetailsPage extends ProductDetailsPageObject {
    public AndroidProductDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    static {
        SCREEN_HEADER = "xpath://android.view.ViewGroup[@content-desc='container header']/android.widget.TextView";
        PRODUCT_PRICE = "accessibility:product price";
        AMOUNT_COUNTER = "xpath://android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView";
        COUNTER_PLUS_BUTTON = "accessibility:counter plus button";
        COUNTER_MINUS_BUTTON = "accessibility:counter minus button";
        ADD_TO_CART_BUTTON = "accessibility:Add To Cart button";
        PRODUCT_DESCRIPTION = "accessibility:product description";
    }
}
