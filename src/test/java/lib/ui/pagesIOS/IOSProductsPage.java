package lib.ui.pagesIOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.pageObjects.ProductsPageObject;

public class IOSProductsPage extends ProductsPageObject {
    public IOSProductsPage(AppiumDriver driver) {
        super(driver);
    }

    static {
        ITEM_BY_NAME_TPL = "xpath://XCUIElementTypeOther[@name='store item']/..//XCUIElementTypeOther[@name='{STRING}']";
        ITEM_BY_INDEX_TPL = "xpath:(//XCUIElementTypeOther[@name='store item'])[{INDEX}]";
        OPEN_MENU_BUTTON = "xpath://XCUIElementTypeButton[@name='tab bar option menu']";
        SORT_BUTTON = "accessibility:sort button";
        CART_BUTTON = "xpath://XCUIElementTypeButton[@name='tab bar option cart']";
        SCREEN_HEADER = "xpath://XCUIElementTypeOther[@name='container header']";
        STORE_ITEMS = "xpath://XCUIElementTypeOther[@name='store item']";
        ITEMS_NAMES = "xpath://XCUIElementTypeOther[@name='store item']/XCUIElementTypeOther[@index=0]";
        ITEMS_PRICES = "xpath://XCUIElementTypeOther[@name='store item']/XCUIElementTypeOther[@index=1]";
        FOOTER = "xpath:(//XCUIElementTypeOther[@name='© 2023 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.'])[1]";

        /* 'Sort by' window */
        SORT_BY_WINDOW_HEADER = "predicate:label == 'Sort by:' AND name == 'Sort by:' AND value == 'Sort by:'";
        ACTIVE_SORTING = "xpath://XCUIElementTypeOther[@name='active option']/..//XCUIElementTypeOther[@index=1]/XCUIElementTypeStaticText";
        NAME_ASC = "accessibility:nameAsc";
        NAME_DESC = "accessibility:nameDesc";
        PRICE_ASC = "accessibility:priceAsc";
        PRICE_DESC = "accessibility:priceDesc";
        /* 'Sort by' window */
    }
}
