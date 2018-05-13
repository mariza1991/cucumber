Feature: login on guru99

  Scenario: Success login on guru99
    Given I am on the login page
    When I fill email field as "email@email.com"
    And I fill password field as "password"
    And I click login btn
    Then The page contains "Signed in as" "email@email.com"