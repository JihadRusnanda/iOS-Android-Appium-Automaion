package stockbit.page_object;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stockbit.driver.DriverInstance;
import stockbit.utils.Config;
import stockbit.utils.Utils;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static stockbit.driver.DriverInstance.driver;
import static stockbit.utils.Config.TIMEOUT;
import static stockbit.utils.Utils.printError;

public class BasePage {
    public AppiumDriver getDriver() {
        return driver;
    }

    public By element(String elementLocator) {
        String elementValue = Utils.ELEMENTS.getProperty(elementLocator);

        if (elementValue == null) {
            printError("Couldn't find element : " + elementLocator + " ! Please check properties file!");
            throw new NoSuchElementException("Couldn't find element : " + elementLocator);
        } else {
            String[] locator = elementValue.split("_");
            String locatorType = locator[0];
            String locatorValue = elementValue.substring(elementValue.indexOf("_") + 1);
            return switch (locatorType) {
                case "id" -> By.id(locatorValue);
                case "name" -> By.name(locatorValue);
                case "xpath" -> By.xpath(locatorValue);
                case "containsText" -> By.xpath(String.format("//*[contains(@text, '%s')]", locatorValue));
                case "cssSelector" -> By.cssSelector(locatorValue);
                case "accessibilityId" -> AppiumBy.accessibilityId(locatorValue);
                default -> throw new IllegalStateException("Unexpected locator type: " + locatorType);
            };
        }
    }

    public WebElement waitUntil(ExpectedCondition<WebElement> expectation) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT));
        return wait.until(expectation);
    }

    public void tap(String element){
        waitUntil(ExpectedConditions.elementToBeClickable(element(element))).click();
    }

    public void typeOn(String element, String text) { // perlu adjust code menyesuaikan dengan ambil dari .properties
        getDriver().findElement(element(element)).sendKeys(text); // di tutorial ini driver()
    }

    public void assertIsDisplayed(String element){ // perlu adjust code menyesuaikan dengan ambil dari .properties
        try {
            getDriver().findElement(element(element)).isDisplayed();
        } catch (NoSuchElementException e) {
            throw new AssertionError(String.format("this element '%s' is not found", element));
        }
    }

    public Boolean isPresent(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), ofSeconds(TIMEOUT));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.ignoring(NoSuchFrameException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isPresent(String element) {
        return isPresent(element(element));
    }

}
