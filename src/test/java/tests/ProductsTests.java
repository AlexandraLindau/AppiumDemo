package tests;

import lib.CoreTestCase;
import lib.ui.ProductDetailsPageObject;
import lib.ui.ProductsPageObject;
import org.junit.Test;
import org.testng.Assert;

import java.util.Map;

public class ProductsTests extends CoreTestCase {

    @Test
    public void testOpenProduct() {

        String product = "Sauce Labs Fleece Jacket";

        ProductsPageObject productsPageObject = new ProductsPageObject(driver);
        productsPageObject.waitForHeaderElement();
        ProductDetailsPageObject productDetailsPageObject = productsPageObject.openProductByName(product);

        String actualProductName = productDetailsPageObject.getProductName();

        Assert.assertEquals(actualProductName, product);
    }

    @Test
    public void testDefaultSorting() {

        String expectedDefaultSorting = "Name - Ascending";

        ProductsPageObject productsPageObject = new ProductsPageObject(driver);
        productsPageObject.waitForHeaderElement();
        productsPageObject.openSortBy();

        String actualDefaultSorting = productsPageObject.getActiveSortingName();

        Assert.assertEquals(actualDefaultSorting, expectedDefaultSorting);
    }

    @Test
    public void testSortProductsByNameDesc() {

        ProductsPageObject productsPageObject = new ProductsPageObject(driver);
        productsPageObject.waitForHeaderElement();
        Map<String, Double> expectedProductListAfterSorting = productsPageObject.reorderProductsByNameDesc();

        productsPageObject.openSortBy();
        productsPageObject.sortByNameDesc();

        Map<String, Double> actualProductListAfterSorting = productsPageObject.getListOfProducts();

        Assert.assertEquals(actualProductListAfterSorting, expectedProductListAfterSorting);
    }
}
