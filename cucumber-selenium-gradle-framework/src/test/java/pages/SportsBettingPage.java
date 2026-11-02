package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class SportsBettingPage {
    private final WebDriver driver;
    private final By headerTitle = By.id("header-title");
    private final By matchSection = By.id("match-section");
    private final By matchListTitle = By.id("match-list-title");
    private final By matchListCount = By.id("match-list-count");
    private final By dateFilterToggle = By.id("date-filter-toggle");
    private final By oddsFilterToggle = By.id("odds-filter-toggle");
    private final By headerBalance = By.id("header-balance");
    private final By headerAvatar = By.id("header-avatar");
    private final By betSlip = By.id("bet-slip");
    private final By betSlipHeader = By.id("bet-slip-header");
    private final By betSlipCount = By.id("bet-slip-count");
    private final By betSlipTitle = By.id("bet-slip-title");
    private final By betSlipBalance = By.id("bet-slip-balance");
    private final By betSlipRemoveAll = By.id("bet-slip-remove-all");
    private final By betSlipSelectionRemove = By.id("bet-slip-selection-remove");
    private final By betSlipTotalStake = By.id("bet-slip-total-stake");
    private final By betSlipPotentialPayout = By.id("bet-slip-potential-payout");
    private final By betSlipInput = By.id("bet-slip-stake-input");
    private final By placeBetButton = By.id("bet-slip-place-bet");
    private final By successModal = By.id("modal-success");
    private final By successModalClose = By.id("modal-success-close-x");
    private final By successModalBetId = By.id("modal-success-bet-id");
    private final By successModalMatch = By.id("modal-success-match");
    private final By successModalStake = By.id("modal-success-stake");
    private final By successModalOdds = By.id("modal-success-odds");
    private final By successModalPayout = By.id("modal-success-payout");
    private final By successModalPlacedAt = By.id("modal-success-placed-at");
    private final By successModalCloseButton = By.id("modal-success-close");
    private final By betSuccessfullyPlaced = By.xpath("//*[text()='Bet Placed Successfully!']");
    private final String homeUrl = "https://qae-assignment-tau.vercel.app/?user-id=candidate-qCuwBbnORjUW";

    /**
     * Initializes the LoginPage Page Object with the provided WebDriver instance.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public SportsBettingPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates the browser directly to the specified URL.
     *
     * @param url The target web address to navigate to.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Launches the SportsBet web application by navigating to the home URL.
     */
    public void launchSportsBetWebApp() {
        navigateTo(homeUrl);
    }

    /**
     * Validates that all primary SportsBet dashboard and header UI elements
     * are visibly displayed on the page.
     */
    public void validateSportsBetUI() {
        List<By> elementsToValidate = Arrays.asList(
                headerTitle, matchSection, matchListTitle, matchListCount,
                dateFilterToggle, oddsFilterToggle, headerBalance, headerAvatar,
                betSlip, betSlipHeader, betSlipCount, betSlipTitle, betSlipBalance
        );
        validateIfListOfElementsAreDisplayedInUI(elementsToValidate);
    }

    /**
     * Selects and clicks the specific odds option for a targeted match based on
     * the desired outcome.
     *
     * @param outcome   The targeted betting outcome (e.g., Home Win, Away Win, Draw).
     * @param matchName The name of the specific match.
     */
    public void selectOddForMatch(String outcome, String matchName) {
        By odd = By.xpath("//*[@id='odds-premier-league-" + matchName + "-" + outcome + "']");
        driver.findElement(odd).click();
    }

    /**
     * Validates that the bet slip container and its critical components
     * are properly generated and visible on the UI.
     */
    public void validateBetSlipGenerated() {
        List<By> elementsToValidate = Arrays.asList(
                betSlipTitle, betSlipRemoveAll, betSlipSelectionRemove, betSlipTotalStake,
                betSlipPotentialPayout);
        validateIfListOfElementsAreDisplayedInUI(elementsToValidate);
    }

    /**
     * Inputs the stake amount, verifies that the updated stake and potential
     * payout values are correctly reflected on the UI, and clicks the place bet button.
     *
     * @param stake           The monetary amount to risk on the bet.
     * @param potentialPayout The expected payout amount to verify against the UI.
     */
    public void placeBetWithStakeAndValidatePotentialPayout(String stake, String potentialPayout) {
        driver.findElement(betSlipInput).sendKeys(stake);
        driver.findElement(By.xpath("//*[@id='bet-slip-total-stake' and contains(text(),'" + stake + "')]")).isDisplayed();
        driver.findElement(By.xpath("//*[@id='bet-slip-potential-payout' and contains(text(),'" + potentialPayout + "')]")).isDisplayed();
        driver.findElement(placeBetButton).click();
    }

    /**
     * Generic helper method that iterates through a list of locators and asserts
     * that every single element is visible on the UI.
     *
     * @param elementsToValidate The list of By locators to evaluate.
     */
    public void validateIfListOfElementsAreDisplayedInUI(List<By> elementsToValidate) {
        for (By locator : elementsToValidate) {
            Assertions.assertTrue(driver.findElement(locator).isDisplayed(), "Element '" + locator.toString() + "' was not displayed!");
        }
    }

    /**
     * Validates that the confirmation receipt elements and details within the
     * successful bet placement modal are fully displayed.
     */
    public void validateIfBetPlacedSuccessfully() {
        List<By> elementsToValidate = Arrays.asList(successModal,
                successModalClose,
                successModalBetId,
                successModalMatch,
                successModalStake,
                successModalOdds,
                successModalPayout,
                successModalPlacedAt,
                successModalCloseButton,
                betSuccessfullyPlaced);
        validateIfListOfElementsAreDisplayedInUI(elementsToValidate);
    }
}
