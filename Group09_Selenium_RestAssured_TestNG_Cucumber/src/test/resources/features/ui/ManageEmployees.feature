@ui
Feature: Manage Employees in IceHRM

  Scenario: Verify the functionality of the Manage Employees link
    Given I am on the IceHRM dashboard
    When I locate the Manage Employees link
    Then I verify the Manage Employees link is functional
