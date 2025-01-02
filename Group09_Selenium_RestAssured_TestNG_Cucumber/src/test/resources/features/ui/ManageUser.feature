@mu
Feature: Manage Users in IceHRM

  Scenario: Navigate to Manage User and add a new user
    Given I open the IceHRM application
    When I enter "admin" as username
    And I enter "admin" as password
    And I click the login button
    Then I should see the dashboard with title "IceHrm"
    When I click the 'Manage User' button on the dashboard
    Then I should see the Manage Users screen
    When I click the 'Add New' button
    Then I should see the user form
    When I fill the form with valid attributes
    And I click the 'Save' button
    Then The new user should be displayed on the screen