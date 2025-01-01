@api2
Feature: Update a Book in the Library

  Scenario: Successfully update a book's title and author
    Given the admin user is authenticated with username "admin" and password "password"
    And a book exists in the database with ID 1
    When I send a PUT request to "/books/1" with updated details:
      | id | title      | author        |
      | 1  | Gone Girl  | Gillian Flynn |
    Then I should receive a 200 response code for update
    And the response should confirm the book was updated

  Scenario: Update a non-existent book with missing author
    Given the admin user is authenticated with username "admin" and password "password"
    When I send a PUT request to "/books/500" with updated details:
      | id  | title      |
      | 500 | The Secret |
    Then I should receive a 400 response code for update
    And the response should indicate "Mandatory parameters should not be null"

  Scenario: Update only the author of a book
    Given the admin user is authenticated with username "admin" and password "password"
    And a book exists in the database with ID 1
    When I send a PUT request to "/books/1" with updated details:
      | id | author       |
      | 1  | Lois Lowry   |
    Then I should receive a 200 response code for update
    And the response should confirm the author was updated

  Scenario: Update book with existing book Id without title parameter
    Given the admin user is authenticated with username "admin" and password "password"
    And a book exists in the database with ID 1
    When I send a PUT request to "/books/1" with updated details:
      | id | author       |
      | 1  | Jane Austen  |
    Then I should receive a 400 response code for update
    And the response should indicate "Mandatory fields are missing"