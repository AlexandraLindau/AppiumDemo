package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTestCase {

    protected AppiumDriver driver;
    protected Platform Platform;


    @Parameters({"deviceName", "platformVersion", "emulator", "udid", "mjpegServerPort"})
    @BeforeMethod
    public void beforeMethod(String deviceName, String platformVersion, boolean emulator,
                             String udid, String mjpegServerPort) throws Exception {
        driver = Platform.getInstance().getDriver(deviceName, platformVersion, emulator, udid, mjpegServerPort);
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

   //Rotate screen to portrait mode
    protected void rotateScreenPortrait() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver driver = (AndroidDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else if (driver instanceof IOSDriver) {
            IOSDriver driver = (IOSDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing on platform "
                    + lib.Platform.getInstance().getPlatformVar());
        }
    }

    //Rotate screen to landscape mode
    protected void rotateScreenLandscape() {
        if (driver instanceof AndroidDriver) {
            AndroidDriver driver = (AndroidDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else if (driver instanceof IOSDriver) {
            IOSDriver driver = (IOSDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing on platform "
                    + lib.Platform.getInstance().getPlatformVar());
        }
    }

    //Run app in background
    protected void backgroundApp(int seconds) {
        if (driver instanceof AndroidDriver) {
            AndroidDriver driver = (AndroidDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else if (driver instanceof IOSDriver) {
            IOSDriver driver = (IOSDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp() does nothing on platform "
                    + lib.Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", lib.Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.out.println("IO problem when writing allure properties file");
        }
    }

    public AppiumDriverLocalService getAppiumServer() {
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.RELAXED_SECURITY));
    }

}
