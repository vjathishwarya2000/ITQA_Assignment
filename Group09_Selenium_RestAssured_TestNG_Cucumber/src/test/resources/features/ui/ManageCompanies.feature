@ManageCompanies
Feature: Manage Companies

  Scenario: Logging in and adding a new company
    Given I am on the login page for the company dashboard
    When I enter the admin username "admin" and password "admin"
    And I click the login button for company
    Then I should be on the company dashboard
    When I navigate to the company structure page
    And I click the Add New button
    And I fill in the new company details with Name "Test Company", Details "Test Details", Address "123 Test Street", Type "Department", and Country "United States"
    And I save the new company
    Then I should see the new company with Name "Test Company", Type "Department", and Country "United States" in the list
