@title @ui
Feature: Login to IceHRM

  Scenario: Successful login with valid credentials
    Given I open the IceHRM application
    When I enter "admin" as username
    And I enter "admin" as password
    And I click the login button for applications
    Then I should see the dashboard with title "IceHrm"