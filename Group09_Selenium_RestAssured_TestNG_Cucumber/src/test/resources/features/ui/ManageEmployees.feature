@manage @ui
Feature: Manage Employees

  Scenario: Logging in, navigating to the Manage Employees page, and searching for a name
    Given I am on the login page
    When I enter my username "admin" and password "admin"
    And I click on the login button
    Then I should be on the dashboard
    When I click on the Manage Employees link
    Then I should be navigated to the Manage Employees page with URL "https://icehrmpro.gamonoid.com/?g=admin&n=employees&m=admin_Employees"
    When I search for an employee with name "ice"


