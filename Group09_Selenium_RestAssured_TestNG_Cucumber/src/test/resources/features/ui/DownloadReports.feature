@download_report @ui
Feature: Download Reports

  Scenario: Generate and Download the Active Employee Report
    Given I am on the login page for the company dashboard
    When I enter the admin username "admin" and password "admin"
    And I click the login button for company
    Then I should be on the company dashboard
    When I click on the "Generate a Report" button
    And I navigate to the Admin Reports page
    And I click the download button for "Active Employee Report"
    And I click the final download button
    And I click the download report link

