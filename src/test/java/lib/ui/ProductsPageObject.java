package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductsPageObject extends MainPageObject {

    public ProductsPageObject(WebDriver driver) {
        super(driver);
    }

    private String ITEM_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{STRING}']/..";
    private String ITEM_BY_INDEX_TPL = "xpath:(//android.widget.TextView[@content-desc='store item text'])[{INDEX}]";
    private String OPEN_MENU_BUTTON = "accessibility:open menu";
    private String SORT_BUTTON = "accessibility:sort button";
    private String CART_BUTTON = "accessibility:cart badge";
    private String SCREEN_HEADER = "accessibility:container header";
    private String STORE_ITEMS = "xpath://android.view.ViewGroup[@content-desc='store item']";
    private String ITEMS_NAMES = "xpath://android.widget.TextView[@content-desc='store item text']";
    private String ITEMS_PRICES = "xpath://android.widget.TextView[@content-desc='store item price']";
    private String FOOTER = "xpath://android.view.ViewGroup[@content-desc='products screen']/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]";

    /* 'Sort by' window */
    private String SORT_BY_WINDOW_HEADER = "xpath://android.widget.TextView[@text='Sort by:']";
    private String ACTIVE_SORTING = "xpath://android.view.ViewGroup[@content-desc='active option']/../android.widget.TextView";
    private String NAME_ASC = "accessibility:nameAsc";
    private String NAME_DESC = "accessibility:nameDesc";
    private String PRICE_ASC = "accessibility:priceAsc";
    private String PRICE_DESC = "accessibility:priceDesc";
    /* 'Sort by' window */


    /* Template methods */

    public ProductDetailsPageObject openProductByName(String name) {
        String locator = ITEM_BY_NAME_TPL.replace("{STRING}", name);
        waitForElementAndClick(locator, "Cannot click element with locator: " + locator, 5);
        return new ProductDetailsPageObject(driver);
    }

    public String getProductNameByIndex(int index) {
        String locator = ITEM_BY_INDEX_TPL.replace("{INDEX}", String.valueOf(index));
        WebElement element = waitForElementPresent(locator, "Cannot find product with locator " + locator, 10);
        return element.getText();
    }

    /* Template methods */

    public void waitForHeaderElement() {
        this.waitForElementPresent(SCREEN_HEADER, "Screen header is not visible", 60);
    }

    public void openSortBy() {
        waitForElementAndClick(SORT_BUTTON, "Cannot click 'Sort by", 10);
        waitForElementPresent(SORT_BY_WINDOW_HEADER, "'Sort by' is now  visible", 10);
    }

    public String getActiveSortingName() {
        WebElement element = waitForElementPresent(ACTIVE_SORTING, "Cannot find 'Active sorting'", 10);
        return element.getText();
    }

    public void scrollDownToSeeAllProducts() {
        By by = getLocatorByString(ITEMS_NAMES);
        List<WebElement> listOfProducts = driver.findElements(by);
        while (!(listOfProducts.size() == 6)) {
            scrollDown();
            listOfProducts = driver.findElements(by);
        }
    }

    public Map<String, Double> getListOfProducts() {

        By products = getLocatorByString(ITEMS_NAMES);
        By prices = getLocatorByString(ITEMS_PRICES);

        int scrollCount = 0;
        Map<String, Double> allProducts = new LinkedHashMap<>();

        boolean endOfPage = false;
        String previousPageSource;

        while (!endOfPage) {

            previousPageSource = driver.getPageSource();

            List<WebElement> listOfProducts = driver.findElements(products);
            List<WebElement> listOfPrices = driver.findElements(prices);

                for (int i = 0; i < listOfProducts.size(); i++) {
                    if (listOfProducts.size() == listOfPrices.size()) {
                        allProducts.put(listOfProducts.get(i).getText(),
                                Double.parseDouble(listOfPrices.get(i).getText().replace("$", "")));
                    } else {
                        break;
                    }
                }

                scrollDown();

                endOfPage = previousPageSource.equals(driver.getPageSource());
                if (!endOfPage) {
                    scrollCount = scrollCount + 1;
                }
            }

        for (int i = 0; i < scrollCount; i++) {
            scrollUp();
        }

        System.out.println("Get all products unsorted");
        printMap(allProducts);

        return allProducts;
    }

    public Map<String, Double> reorderProductsByNameDesc() {
        Map<String, Double> productsBefore = new HashMap<>(getListOfProducts());
        Map<String, Double> productsAfter = new LinkedHashMap<>();
        List<String> list = new ArrayList<>(productsBefore.keySet());
        Collections.sort(list, Collections.reverseOrder());
        list.sort(Collections.reverseOrder());
        for (int i = 0; i < list.size(); i++) {
            productsAfter.put(list.get(i), productsBefore.get(list.get(i)));
        }

        System.out.println("Get all products sorted by name Desc");
        printMap(productsAfter);
        return productsAfter;
    }

//    public Map<String, Double> reorderProductsByNameAsc() {
//        Map<String, Double> list = new HashMap<>(getListOfProductsByName());
//        Collections.sort(list, Collections.reverseOrder());
//        list.sort(Collections.reverseOrder());
//        Collections.reverse(list);
//        return list;
//    }

    public void sortByNameDesc() {
        waitForElementAndClick(NAME_DESC, "Cannot select sorting by name - DESC", 10);
    }

    public void sortByNameAsc() {
        waitForElementAndClick(NAME_ASC, "Cannot select sorting by name - ASC", 10);
    }

    public void printMap(Map<String, Double> hashMap) {
        hashMap.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        System.out.println("\n");
    }
}

