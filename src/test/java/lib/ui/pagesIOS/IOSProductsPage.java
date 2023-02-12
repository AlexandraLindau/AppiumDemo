package lib.ui.pagesIOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.pageObjects.ProductsPageObject;

public class IOSProductsPage extends ProductsPageObject {
    public IOSProductsPage(AppiumDriver driver) {
        super(driver);
    }

    static {
        ITEM_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{STRING}']/..";
        ITEM_BY_INDEX_TPL = "xpath:(//android.widget.TextView[@content-desc='store item text'])[{INDEX}]";
        OPEN_MENU_BUTTON = "accessibility:open menu";
        SORT_BUTTON = "accessibility:sort button";
        CART_BUTTON = "accessibility:cart badge";
        SCREEN_HEADER = "accessibility:container header";
        STORE_ITEMS = "xpath://android.view.ViewGroup[@content-desc='store item']";
        ITEMS_NAMES = "xpath://android.widget.TextView[@content-desc='store item text']";
        ITEMS_PRICES = "xpath://android.widget.TextView[@content-desc='store item price']";
        FOOTER = "xpath://android.view.ViewGroup[@content-desc='products screen']/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]";

        /* 'Sort by' window */
        SORT_BY_WINDOW_HEADER = "xpath://android.widget.TextView[@text='Sort by:']";
        ACTIVE_SORTING = "xpath://android.view.ViewGroup[@content-desc='active option']/../android.widget.TextView";
        NAME_ASC = "accessibility:nameAsc";
        NAME_DESC = "accessibility:nameDesc";
        PRICE_ASC = "accessibility:priceAsc";
        PRICE_DESC = "accessibility:priceDesc";
        /* 'Sort by' window */
    }
}
