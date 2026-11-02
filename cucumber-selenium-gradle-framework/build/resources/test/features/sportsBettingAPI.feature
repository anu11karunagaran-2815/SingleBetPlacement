Feature: Sports Betting API test

  Background: Set base url
    Given the API base URI is "https://qae-assignment-tau.vercel.app"
    And the header is set "candidate-qCuwBbnORjUW"
    And the content type header is set to JSON

  Scenario:GET matches
    When a GET request is sent to "/api/matches"
    Then the response status code should be 200
    And I validate the response body matches json content "TestData/matchesResponse.json"

  Scenario:GET balance
    When a GET request is sent to "/api/balance"
    Then the response status code should be 200

  Scenario:Reset balance
    When a POST request is sent to "/api/reset-balance"
    Then the response status code should be 200
    And the response body field "message" should match "Balance reset successfully"


  Scenario:Place Bet
    When a POST request is sent to "/api/place-bet" with body:
      """
      {
        "matchId": "premier-league-manutd-chelsea",
        "selection": "HOME",
        "stake": 10,
        "additionalProp1": {}
      }
      """
    Then the response status code should be 200
    And the response body field "message" should match "Bet placed successfully"


