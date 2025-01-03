@delete-book @api
Feature: Delete a Book from the Library

  Scenario: Successfully delete a book without authorization
    Given the user is not authenticated
    When I send a DELETE request to "/books/3" with:
      | id |
      | 3  |
    Then I should receive a 401 delete response code
    And the response should confirm the lack of authorization

  Scenario: Successfully delete a book as admin
    Given the user is authenticated with username "admin" and password "password" to delete a book
    When I send a DELETE request to "/books/4" with:
      | id |
      | 4  |
    Then I should receive a 200 delete response code
    And the response should confirm the book was deleted

  Scenario: Successfully delete a book as a standard user
    Given the user is authenticated with username "user" and password "password" to delete a book
    When I send a DELETE request to "/books/5" with:
      | id |
      | 5  |
    Then I should receive a 403 delete response code
#    And the response should confirm the book was deleted

  Scenario: Delete a non-existent book as admin
    Given the user is authenticated with username "admin" and password "password" to delete a book
    When I send a DELETE request to "/books/9999" with:
      | id    |
      | 9999  |
    Then I should receive a 404 delete response code
    And the response should confirm the book was not found

  Scenario: Delete a non-existent book as user
    Given the user is authenticated with username "user" and password "password" to delete a book
    When I send a DELETE request to "/books/999" with:
      | id   |
      | 999  |
    Then I should receive a 403 delete response code
    And the response should confirm the lack of authorization

