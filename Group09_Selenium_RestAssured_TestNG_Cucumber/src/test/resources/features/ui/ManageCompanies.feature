@ManageCompanies
Feature: Manage Companies

  Scenario: Logging in and clicking the Manage Company button
    Given I am on the login page for the company dashboard
    When I enter the admin username "admin" and password "admin"
    And I click the login button for company
    Then I should be on the company dashboard
    When I navigate to the company structure page
