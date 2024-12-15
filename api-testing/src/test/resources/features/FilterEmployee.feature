Feature: View Employee Details
  As an admin user
  I want to navigate to the Employees page
  So that I can view the details of employees

  Scenario: Verify employee details on Employees page
    Given I am logged into IceHRM
    When I navigate to the Employees page
    Then I should see the employee details table
