@api
Feature: Add a New Book to the Library

  Scenario: Successfully create a new book as admin
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | id    | title      | author     |
      | 12345 | "Book A"   | "Author A" |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Successfully create a new book as a standard user
    Given the user is authenticated with username "user" and password "password"
    When I send a POST request to "/books" with:
      | id    | title      | author     |
      | 67890 | "Book B"   | "Author B" |
    Then I should receive a 201 response code
    And the response should confirm the book was created