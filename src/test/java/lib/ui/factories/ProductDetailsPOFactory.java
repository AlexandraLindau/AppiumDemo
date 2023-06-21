package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.pageObjects.ProductDetailsPageObject;
import lib.ui.pagesAndroid.AndroidProductDetailsPage;
import lib.ui.pagesIOS.IOSProductDetailsPage;

public class ProductDetailsPOFactory {

    public static ProductDetailsPageObject get(AppiumDriver driver) throws Exception {
        if(Platform.getInstance().isAndroid()) {
            return new AndroidProductDetailsPage(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSProductDetailsPage(driver);
        } else {
            throw new Exception("Invalid platform " + Platform.getPlatformVar());
        }
    }
}
