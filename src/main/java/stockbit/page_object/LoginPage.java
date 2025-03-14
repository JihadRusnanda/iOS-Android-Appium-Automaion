package stockbit.page_object;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static stockbit.utils.Config.ANDROID;
import static stockbit.utils.Config.IOS;
import static stockbit.utils.Config.TIMEOUT;
import static stockbit.utils.Utils.env;
import static stockbit.utils.Utils.printError;

public class LoginPage extends BasePage {

    public void tapLoginOnboarding() {
        if (env("PLATFORM_VERSION").contains(ANDROID)) {
            tap("BUTTON_LOGIN");
        } else if (env("PLATFORM_VERSION").contains(IOS)) {
            tap("ENTRYPOINT_BUTTON_LOGIN");
        }
    }

    public void popupAction(String action) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), ofSeconds(TIMEOUT));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            switch (action.toUpperCase()) {
                case "ACCEPT" -> alert.accept();
                case "DISMISS" -> alert.dismiss();
                default -> throw new IllegalStateException("Unexpected value: " + action.toUpperCase());
            }
        } catch (NoAlertPresentException | TimeoutException e) {
            printError("error");
        }
    }

    public void isOnboardingPages() {
        if (env("PLATFORM_VERSION").contains(ANDROID)) {
            assertIsDisplayed("ONBOARDING_PAGES_SOCIAL");
        } else if (env("PLATFORM_VERSION").contains(IOS)) {
            popupAction("DISMISS");
            tap("BUTTON_NEXT_TRACK_APP");
            popupAction("ACCEPT");

            if (isPresent("BUTTON_NEXT_TRACK_APP")) {
                tap("BUTTON_NEXT_TRACK_APP");
            }
        }
    }

    public void inputUsername(String username) {
        if (env("PLATFORM_VERSION").contains(ANDROID)) {
            typeOn("INPUT_USERNAME", username);
        } else if (env("PLATFORM_VERSION").contains(IOS)) {
            typeOn("FIELD_USERNAME_LOGIN", username);
        }

    }

    public void inputPassword(String password) {
        typeOn(("INPUT_PASSWORD"), password);
    }

    public void seePassword() {
        tap("SEE_PASSWORD");
    }

    public void tapLoginSubmit() {
        tap("BUTTON_SUBMIT_LOGIN");
    }

    public void tapSkipBiometricPopup() {
        tap("BUTTON_SKIP_BIOMETRIC");
    }

    public void tapSkipChooseAvatar() {
        tap("BUTTON_SKIP_AVATAR");
    }

    public void isWatchlistMainPages() throws InterruptedException {
        tapSkipBiometricPopup();
        tapSkipChooseAvatar();
        assertIsDisplayed("ONBOARDING_WATCHLIST");
        Thread.sleep(3000);
    }

    public void tapPortfolio() {
        tap("BUTTON_TAP_PORTOFLIO");
    }

}
