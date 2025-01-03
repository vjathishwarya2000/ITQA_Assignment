@filter @ui
Feature: Manage Permissions

  Background:
    Given I am on the login page for the company dashboard
    When I enter the admin username "admin" and password "admin"
    And I click the login button for company
    Then I should be on the company dashboard

  Scenario: Filter permissions list by Finance salary
    When I navigate to the permissions page
    And I click the filter button
    And I select "Finance salary" from the All Modules dropdown
    And I click the apply filter button
    Then the permissions list should be filtered by "Finance salary"

