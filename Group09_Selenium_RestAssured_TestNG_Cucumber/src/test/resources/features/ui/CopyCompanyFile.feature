@copy
Feature: Copy Company Document
  As an admin
  I want to copy a document from the document types list
  So that I can create a duplicate document for similar purposes

  Background:
    Given I am logged in and on the Dashboard

  Scenario: Copying any company document dynamically
    When I navigate to the Document Types page
    And I click the Copy button for the first available document
    Then A copy of the document should be created
    And I click the Save button to save the copied document

