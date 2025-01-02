@editUser
Feature: Edit User in IceHRM
  This feature allows users to navigate to the Manage User page, add a new user, and then edit their details.

  Scenario: Navigate to Manage User, add a new user, and edit the user details
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
    When I edit the user "Maranmax" with new details "Dhoni", "dhoni@gmail.com"
    Then The updated user details should be displayed on the screen with "Dhoni"
