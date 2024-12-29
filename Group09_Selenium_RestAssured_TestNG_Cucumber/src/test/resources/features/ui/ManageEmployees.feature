@manage
Feature: Manage Employees

  Scenario: Logging in and navigating to the Manage Employees page
    Given I am on the login page
    When I enter my username "admin" and password "admin"
    And I click on the login button
    Then I should be on the dashboard
    When I click on the Manage Employees link
    Then I should be navigated to the Manage Employees page with URL "https://icehrmpro.gamonoid.com/?g=admin&n=employees&m=admin_Employees"
