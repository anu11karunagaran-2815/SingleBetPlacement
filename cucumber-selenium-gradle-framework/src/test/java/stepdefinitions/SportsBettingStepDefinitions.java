package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import pages.SportsBettingPage;

public class SportsBettingStepDefinitions {
    private final WebDriver driver = Hooks.getDriver();
    private final SportsBettingPage sportsBettingPage = new SportsBettingPage(driver);

    @Given("I navigate to sports betting app")
    public void the_user_is_on_the_login_page() {
        sportsBettingPage.launchSportsBetWebApp();
    }

    @And("I validate the sports bet UI")
    public void validate_sports_bet_ui() {
        sportsBettingPage.validateSportsBetUI();
    }

    @And("I select odd on {string} for {string}")
    public void place_bet(String homeAway, String matchName) {
        sportsBettingPage.selectOddForMatch(homeAway, matchName);
    }

    @And("I validate bet slip generated")
    public void validate_bet_slip_generated() {
        sportsBettingPage.validateBetSlipGenerated();
    }

    @And("I enter stake {string} validate potential payout {string} and place bet")
    public void place_bet_with_stake(String stake, String potentialPayout) {
        sportsBettingPage.placeBetWithStakeAndValidatePotentialPayout(stake, potentialPayout);
    }

    @And("I validate if bet placed successfully")
    public void validate_if_bet_placed_successfully() {
        sportsBettingPage.validateIfBetPlacedSuccessfully();
    }
}
