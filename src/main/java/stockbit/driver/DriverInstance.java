package stockbit.driver;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Capabilities;
import stockbit.utils.CapabilityFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import static stockbit.utils.Config.APPIUM_LOCAL_URL;
import static stockbit.utils.Config.TIMEOUT;

public class DriverInstance {
    public static AppiumDriver driver;

    private DriverInstance() {
        throw new UnsupportedOperationException("Utility Class Not Initiate");
    }

    public static void initializeDriver(String platformName, String platformVersion, String udid) {
        Capabilities capabilities = CapabilityFactory.getCapabilities(platformName, platformVersion, udid);
        try {
            URL appiumServerUrl = URI.create(APPIUM_LOCAL_URL).toURL();
            driver = new AppiumDriver(appiumServerUrl, capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void teardownDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
