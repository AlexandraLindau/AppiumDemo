package tests;

import lib.CoreTestCase;
import lib.ui.factories.ProductDetailsPOFactory;
import lib.ui.factories.ProductsPOFactory;
import lib.ui.pageObjects.ProductDetailsPageObject;
import lib.ui.pageObjects.ProductsPageObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ProductsTests extends CoreTestCase {

    @Test
    public void testOpenProduct() throws Exception {

        String product = "Sauce Labs Fleece Jacket";

        ProductsPageObject productsPage = ProductsPOFactory.get(driver);
        productsPage.waitForHeaderElement();
        productsPage.openProductByName(product);

        ProductDetailsPageObject productDetailsPage = ProductDetailsPOFactory.get(driver);
        productDetailsPage.waitForHeaderElement();

        String actualProductName = productDetailsPage.getProductName();

        Assert.assertEquals(actualProductName, product);
    }

    @Test
    public void testDefaultSorting() throws Exception {

        String expectedDefaultSorting = "Name - Ascending";

        ProductsPageObject productsPage = ProductsPOFactory.get(driver);
        productsPage.waitForHeaderElement();
        productsPage.openSortBy();

        String actualDefaultSorting = productsPage.getActiveSortingName();

        Assert.assertEquals(actualDefaultSorting, expectedDefaultSorting);
    }

    @Test
    public void testSortProductsByNameDesc() throws Exception {

        ProductsPageObject productsPage = ProductsPOFactory.get(driver);
        productsPage.waitForHeaderElement();
        Map<String, Double> expectedProductListAfterSorting = productsPage.reorderProductsByNameDesc();

        productsPage.openSortBy();
        productsPage.sortByNameDesc();
        productsPage.waitForHeaderElement();

        Map<String, Double> actualProductListAfterSorting = productsPage.getListOfProducts();

        Assert.assertEquals(actualProductListAfterSorting, expectedProductListAfterSorting);
    }
}
