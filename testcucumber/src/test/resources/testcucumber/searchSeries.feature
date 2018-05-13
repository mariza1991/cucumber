Feature: search series

  Scenario: Finding existing series
    Given I am on the seasonvar main page
    When I input "Misfits"
    And Click search button
    Then The page contains block with results "Misfits"