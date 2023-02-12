package lib.ui.pageObjects;

import io.appium.java_client.AppiumDriver;
import lib.ui.factories.ProductDetailsPOFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductsPageObject extends lib.ui.MainPageObject {

    public ProductsPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
    ITEM_BY_NAME_TPL,
    ITEM_BY_INDEX_TPL,
    OPEN_MENU_BUTTON,
    SORT_BUTTON,
    CART_BUTTON,
    SCREEN_HEADER,
    STORE_ITEMS,
    ITEMS_NAMES,
    ITEMS_PRICES,
    FOOTER,

    /* 'Sort by' window */
    SORT_BY_WINDOW_HEADER,
    ACTIVE_SORTING,
    NAME_ASC,
    NAME_DESC,
    PRICE_ASC,
    PRICE_DESC;
    /* 'Sort by' window */


    /* Template methods */

    public ProductDetailsPageObject openProductByName(String name) throws Exception {
        String locator = ITEM_BY_NAME_TPL.replace("{STRING}", name);
        scrollToFindElement(true, locator, 10);
        waitForElementAndClick(locator, "Cannot click element with locator: " + locator, 5);
        return ProductDetailsPOFactory.get(driver);
    }

    public String getProductNameByIndex(int index) {
        String locator = ITEM_BY_INDEX_TPL.replace("{INDEX}", String.valueOf(index));
        WebElement element = waitForElementPresent(locator, "Cannot find product with locator " + locator, 10);
        return element.getText();
    }

    /* Template methods */

    public void waitForHeaderElement() {
//        scrollToFindElement(false, SCREEN_HEADER, 10);
        this.waitForElementPresent(SCREEN_HEADER, "Screen header is not visible", 60);
    }

    public void openSortBy() {
        waitForElementAndClick(SORT_BUTTON, "Cannot click 'Sort by", 10);
        waitForElementPresent(SORT_BY_WINDOW_HEADER, "'Sort by' is not  visible", 10);
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

