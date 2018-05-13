Feature: google

Scenario: Finding some cars
  Given I am on the Google Search page
  When I search for "cars"
  Then The page title contains "cars"
