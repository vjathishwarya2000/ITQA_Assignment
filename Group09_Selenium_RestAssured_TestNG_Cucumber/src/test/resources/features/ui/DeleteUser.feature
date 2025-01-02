@deleteUser
Feature: Delete User in IceHRM

  Background: Add a new user
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
    And I click the OK button on the confirmation modal
    Then The new user should be displayed on the screen

  Scenario: Delete the newly added user
    When I search for the user "Maranmax"
    Then I should see the user "Maranmax" in the search results
    When I delete the user "Maranmax"
    Then I search for the user "Maranmax" again
    Then I should not see the user "Maranmax" in the search results
