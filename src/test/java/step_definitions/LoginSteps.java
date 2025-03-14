package step_definitions;


import io.cucumber.java8.En;
import stockbit.page_object.LoginPage;


public class LoginSteps implements En {
    LoginPage loginPage = new LoginPage();

    public LoginSteps() {
        Given("^User is on stockbit landingpage$", () -> loginPage.isOnboardingPages());

        When("^User click login$", () -> loginPage.tapLoginOnboarding());

        And("^User input username as \"([^\"]*)\"$", (String username) -> loginPage.inputUsername(username));

        And("^User input password as \"([^\"]*)\"$", (String password) -> loginPage.inputPassword(password));

        And("^User see the password$", () -> loginPage.seePassword());

        And("^User click button login$", () -> loginPage.tapLoginSubmit());

        And("^User see watchlist page$", () -> loginPage.isWatchlistMainPages());

        And("^User click portfolio", () -> loginPage.tapPortfolio());
    }
}
