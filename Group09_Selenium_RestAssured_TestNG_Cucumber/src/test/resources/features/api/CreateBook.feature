@api
Feature: Add a New Book to the Library

  Scenario: Successfully create a new book as admin
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | id    | title       | author     |
      |       | "Book Ab"   | "Author A" |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Successfully create a new book as a standard user
    Given the user is authenticated with username "user" and password "password"
    When I send a POST request to "/books" with:
      | id    | title       | author     |
      |       | "Book Ba"   | "Author B" |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Create book with missing title field
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | id | title | author       |
      |    |       | "John Doe"   |
    Then I should receive a 400 response code
    And the response should contain an error message "Title is required"

  Scenario: Create book with missing author field
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | id | title         | author |
      |    | "Sample Book" |        |
    Then I should receive a 400 response code
    And the response should contain an error message "Author is required"

  Scenario: Successfully create a book with a specific ID
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | id    | title       | author     |
      | 123   | "Book ID"   | "Author ID" |
    Then I should receive a 201 response code
    And the response should confirm the book was created with the given ID
