@wip
Feature: API Data Analysis

Background:
  Given The endpoint is "https://api.wazirx.com/sapi/v1/tickers/24hr"

  Scenario: Get symbols with open price less than low price
    When I filter symbols whose open price is less than low price
    And I verify that the response contains the filtered symbols

  Scenario: Get symbols and base assets with volume more than average of all volumes
    When I calculate the average of all volumes in the list
    And I filter symbols and base assets whose volume is more than the calculated average

  Scenario: Find 5 assets/symbols with least differences between bidPrice and askPrice
    When I calculate the difference between bidPrice and askPrice for each asset symbol
    And I sort the assets symbols based on the differences in ascending order
    And I select the top 5 assets symbols with the least differences

  Scenario: Verify API health status
    When the response status code should be 200 OK
    And the response time should be less than 2500 milliseconds
    And the response should be in "application/json; charset=utf-8" valid JSON format

  Scenario: Verify API response for invalid request
    When I send a DELETE request to the API
    Then the response status code should be 403 Forbidden
