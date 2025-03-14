package stockbit.utils;


import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;

import java.time.Duration;

import static stockbit.utils.Config.ANDROID;
import static stockbit.utils.Config.IOS;

public class CapabilityFactory {
    private CapabilityFactory() {
        throw new UnsupportedOperationException("Utility class tidak boleh diinstansiasi");
    }

    public static Capabilities getCapabilities(String platformName, String platformVersion, String udid) {
        if (platformName.equalsIgnoreCase(ANDROID)) {
            return getAndroidCapabilities(platformVersion);
        } else if (platformName.equalsIgnoreCase(IOS)) {
            return getIosCapabilities(platformVersion, udid);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platformName);
        }
    }

    private static UiAutomator2Options getAndroidCapabilities(String platformVersion) {
        return new UiAutomator2Options()
                .setPlatformName(ANDROID)
                .setPlatformVersion(platformVersion)
                .setAutomationName("UiAutomator2")
                .setDeviceName("emulator-5554")
                .setApp("/Users/muhammadjihad/Downloads/2.35.1-rc3(11030).apk") // Hardcoded for now
                .autoGrantPermissions()
                .setAppWaitActivity("*")
                .setNoReset(false);
    }

    private static XCUITestOptions getIosCapabilities(String platformVersion, String udid) {
        return new XCUITestOptions()
                .setPlatformName(IOS)
                .setPlatformVersion(platformVersion)
                .setDeviceName("")
                .setAutomationName("XCUITest")
                .setApp("/Users/muhammadjihad/Downloads/Stockbit Build 35950 Build Products for Stockbit on iOS/UIAutomation Simulator Prod-iphonesimulator/Stockbit.app") // Hardcoded for now
                .setUdid(udid)
                .setWdaStartupRetries(3)
                .setWdaLaunchTimeout(Duration.ofSeconds(100))
                .setUpdatedWdaBundleId("com.stockbit.WebDriverAgentRunner")
                .setUsePrebuiltWda(true)
                .setUseNewWDA(false)
                .setNoReset(false);
    }
}


