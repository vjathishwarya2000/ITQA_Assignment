@create-book @api
Feature: Add a New Book to the Library

  Scenario: Successfully create a new book as admin
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | title                             | author              |
      | The Catcher in the Rye            | J.D. Salinger       |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Successfully create a book with a specific ID
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | id    | title                   | author          |
      | 452   | The Odyssey             | Homer           |
    Then I should receive a 201 response code
    And the response should confirm the book was created with the given ID

  Scenario: Successfully create a book with the same title but different authors
    Given the user is authenticated with username "admin" and password "password"
    And a book already exists with:
      | title              | author          |
      | The Alchemist      | Paulo Coelho    |
    When I send a POST request to "/books" with:
      | title              | author          |
      | The Alchemist      | Eric Wall       |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Successfully create a book with the same author but different title
    Given the user is authenticated with username "admin" and password "password"
    And a book already exists with:
      | title                     | author         |
      | The Hobbit                | J.R.R. Tolkien |
    When I send a POST request to "/books" with:
      | title                     | author         |
      | The Lord of the Rings     | J.R.R. Tolkien |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Prevent creating a book with duplicate data
    Given the user is authenticated with username "admin" and password "password"
    And a book already exists with:
      | title           | author        |
      | Brave New World | Aldous Huxley |
    When I send a POST request to "/books" with:
      | title           | author        |
      | Brave New World | Aldous Huxley |
    Then I should receive a 208 response code

  Scenario: Successfully create a new book as a standard user
    Given the user is authenticated with username "user" and password "password"
    When I send a POST request to "/books" with:
      | title                             | author              |
      | 1984                              | George Orwell       |
    Then I should receive a 201 response code
    And the response should confirm the book was created

  Scenario: Create book with missing title field
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | title | author         |
      |       | Leo Tolstoy    |
    Then I should receive a 400 response code

  Scenario: Create book with missing author field
    Given the user is authenticated with username "admin" and password "password"
    When I send a POST request to "/books" with:
      | title                   | author |
      | War and Peace           |        |
    Then I should receive a 400 response code