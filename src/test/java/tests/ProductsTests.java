package tests;

import lib.CoreTestCase;
import lib.ui.ProductDetailsPageObject;
import lib.ui.ProductsPageObject;
import org.junit.Test;
import org.testng.Assert;

import java.util.List;

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
        List<String> expectedProductListAfterSorting = productsPageObject.reorderProductsByNameDesc();

        productsPageObject.openSortBy();
        productsPageObject.sortByNameDesc();
        productsPageObject.scrollDownToSeeAllProducts();

        Assert.assertEquals(productsPageObject.getProductNameByIndex(1), expectedProductListAfterSorting.get(0));
        Assert.assertEquals(productsPageObject.getProductNameByIndex(2), expectedProductListAfterSorting.get(1));
        Assert.assertEquals(productsPageObject.getProductNameByIndex(3), expectedProductListAfterSorting.get(2));
        Assert.assertEquals(productsPageObject.getProductNameByIndex(4), expectedProductListAfterSorting.get(3));
        Assert.assertEquals(productsPageObject.getProductNameByIndex(5), expectedProductListAfterSorting.get(4));
        Assert.assertEquals(productsPageObject.getProductNameByIndex(6), expectedProductListAfterSorting.get(5));
    }
}
