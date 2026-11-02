Feature: Single Bet Placement
  Background: Launch sports betting web app
    Given I navigate to sports betting app

  Scenario: Place bet on Manchester Utd vs Chelsea - E2E UI test
    Given I navigate to sports betting app
    And I validate the sports bet UI
    And I select odd on "home" for "manutd-chelsea"
    And I validate bet slip generated
    And I enter stake "1" validate potential payout "€2.45" and place bet
    And I validate if bet placed successfully
    




