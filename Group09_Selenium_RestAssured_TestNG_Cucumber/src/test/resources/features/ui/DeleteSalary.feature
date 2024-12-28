@salary
Feature: Manage Salary Components

  Scenario: Successfully delete the first occurrence of a salary component
    Given I am logged in with valid credentials
    And I am on the Salary Component page
    When I delete the salary component "Telephone Allowance"
    Then The confirmation alert for deleting the salary component "Telephone Allowance" should be displayed
