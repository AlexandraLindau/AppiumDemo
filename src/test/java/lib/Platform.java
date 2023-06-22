package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_ANDROID = "Android";
    private static final String APPIUM_URL = "http://0.0.0.0:4723/wd/hub";
    File classpathRoot = new File(System.getProperty("user.dir"));
    File appDir = new File(classpathRoot, "src/test/apps/");

    private static Platform instance;

    public Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        } return instance;
    }

    public AppiumDriver getDriver(String deviceName, String platformVersion, boolean emulator,
                                  String udid, String mjpegServerPort) throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities(deviceName, platformVersion, emulator, udid, mjpegServerPort));
        } else if (isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities(deviceName, platformVersion, udid, mjpegServerPort));
        } else {
            throw new Exception("Cannot detect type of driver. Platform value: " + getPlatformVar());
        }
    }

    public static boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public static boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }


    private DesiredCapabilities getAndroidDesiredCapabilities(String deviceName, String platformVersion, boolean emulator,
                                                              String udid, String mjpegServerPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("emulator", emulator);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("mjpegServerPort", mjpegServerPort);
        capabilities.setCapability("automationName", "UIAutomator2");
        capabilities.setCapability("appPackage", "com.saucelabs.mydemoapp.rn");
        File app = new File(appDir, "Android-MyDemoAppRN.1.3.0.build-244.apk");
        capabilities.setCapability("app", app.getAbsolutePath());
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(String deviceName, String platformVersion,
                                                          String udid, String mjpegServerPort) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("mjpegServerPort", mjpegServerPort);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/alexandra/Desktop/JavaAppiumAutomation2/apks/Wikipedia.app");
        File app = new File(appDir, "MyRNDemoApp.app");
        capabilities.setCapability("app", app.getAbsolutePath());
        return capabilities;
    }


    private static boolean isPlatform(String my_platform) {
        String platform = getPlatformVar();
        return my_platform.equals(platform);
    }

    public static String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

}
