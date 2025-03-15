package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import stockbit.driver.DriverInstance;

import java.net.MalformedURLException;

import static stockbit.utils.Config.ANDROID;
import static stockbit.utils.Config.ELEMENTS;
import static stockbit.utils.Utils.env;
import static stockbit.utils.Utils.loadElementProperties;

public class DriverHooks {
    @BeforeAll
    public static void loadElement() {
        loadElementProperties(ELEMENTS);
    }

    @Before("@Android or @iOS")
    public void setupDriver() {
        String platformName = env("PLATFORM_VERSION");
        String platformVersion = platformName.equals(ANDROID) ? "12.0" : "";
        String udid = platformName.equals(ANDROID) ? "emulator-5554" : "";

        DriverInstance.initializeDriver(platformName, platformVersion, udid);
    }

    @After("@Android or @iOS")
    public void quitDriver(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverInstance.driver)
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Screenshot");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverInstance.teardownDriver();
        }
    }
}
