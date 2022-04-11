package lib.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPageObject extends MainPageObject {

    public ProductDetailsPageObject(WebDriver driver) {
        super(driver);
    }

    private String SCREEN_HEADER = "xpath://android.view.ViewGroup[@content-desc='container header']/android.widget.TextView";
    private String PRODUCT_PRICE = "accessibility:product price";
    private String ITEMS_NAMES = "xpath://android.widget.TextView[@content-desc='store item text']";
    private String ITEMS_PRICES = "xpath://android.widget.TextView[@content-desc='store item price']";
    private String AMOUNT_COUNTER = "xpath://android.view.ViewGroup[@content-desc='counter amount']/android.widget.TextView";
    private String COUNTER_PLUS_BUTTON = "accessibility:counter plus button";
    private String COUNTER_MINUS_BUTTON = "accessibility:counter minus button";
    private String ADD_TO_CART_BUTTON = "accessibility:Add To Cart button";
    private String PRODUCT_DESCRIPTION = "accessibility:product description";

    public String getProductName() {
        WebElement element = waitForElementPresent(SCREEN_HEADER, "Cannot find screen header", 10);
        return element.getText();
    }

}

