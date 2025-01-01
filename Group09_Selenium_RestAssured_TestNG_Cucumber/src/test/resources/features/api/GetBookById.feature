@api
Feature: Get Book By ID

  # Scenario 1: Retrieve a book with a non-existent ID
  Scenario: Attempt to retrieve a book with a non-existent ID
    Given the user is authenticated for retrieving books with username "admin" and password "password"
    When I send a GET request to "/api/books/888"
    Then I should receive a 404 response code for retrieving books
    And the response body should contain an error message "Book not found"

  # Scenario 2: Retrieve a book without providing credentials
  Scenario: Attempt to retrieve a book without providing credentials
    When I send an unauthorized GET request to "/api/books/1"
    Then I should receive a 401 Unauthorized response code
    And the response body should contain an error message "Authentication is required"

  # Scenario 3: Retrieve an existing book with valid credentials
  Scenario: Successfully retrieve a book with valid credentials
    Given the user is authenticated for retrieving books with username "admin" and password "password"
    When I send a GET request to "/api/books/1"
    Then I should receive a 200 response code for retrieving books
    And the response body should contain the book details:
      | id    | title       | author    |
      | 1     | "Wonderland" | "John"   |

  # Scenario 4: Attempt to retrieve a book with an invalid ID format
  Scenario: Attempt to retrieve a book with an invalid ID format
    Given the user is authenticated for retrieving books with username "admin" and password "password"
    When I send a GET request to "/api/books/abc"
    Then I should receive a 400 response code for retrieving books
    And the response body should contain an error message "Invalid ID format"

  # Scenario 5: Attempt to retrieve a book with valid credentials but unauthorized user
  Scenario: Retrieve a book with valid user credentials but unauthorized
    Given the user is authenticated for retrieving books with username "user" and password "password"
    When I send a GET request to "/api/books/1"
    Then I should receive a 401 Unauthorized response code
    And the response body should contain an error message "User is not permitted"
