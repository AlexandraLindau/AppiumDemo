package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.pageObjects.ProductsPageObject;
import lib.ui.pagesAndroid.AndroidProductsPage;
import lib.ui.pagesIOS.IOSProductsPage;

public class ProductsPOFactory {

    public static ProductsPageObject get(AppiumDriver driver) throws Exception {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidProductsPage(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSProductsPage(driver);
        } else {
            throw new Exception("Invalid platform " + Platform.getPlatformVar());
        }
    }
}
