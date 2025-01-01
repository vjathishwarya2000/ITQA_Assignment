@api3
Feature: Update an Existing Book in the Library

  Scenario: Successfully update an existing book as an admin
    Given the user is authenticated with username "admin" and password "password" to update a book
    And a book exists with ID 1, title "Book A", and author "Author A"
    When I send a PUT request to "/books" with updated details:
      | id | title    | author     |
      | 1  | "Book W" | "Author W" |
    Then I should receive a 200 response code for the update operation
    And the response should confirm the book was updated

  Scenario: Attempt to update a non-existent book
    Given the user is authenticated with username "admin" and password "password" to update a book
    When I send a PUT request to "/books" with updated details:
      | id    | title    | author     |
      | 99999 | "Book X" | "Author X" |
    Then I should receive a 404 response code for the update operation
    And the response should indicate the book was not found