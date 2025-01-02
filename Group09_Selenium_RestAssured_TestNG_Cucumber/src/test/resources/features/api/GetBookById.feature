@api1
Feature: Get Book By ID
  The feature verifies the ability to retrieve book details by ID under different conditions.

  # Scenario 1: Retrieve a book with a non-existent ID as admin
  Scenario: Attempt to retrieve a book with a non-existent ID as admin
    Given the user is authenticated for retrieving books with username "admin" and password "password"
    When I send a GET request to "/api/books/800"
    Then I should receive a 404 response code for retrieving books
    And the response body should contain an error message "Book not found"

  # Scenario 2: Retrieve a book with a non-existent ID as a regular user
  Scenario: Attempt to retrieve a book with a non-existent ID as a regular user
    Given the user is authenticated for retrieving books with username "user" and password "password"
    When I send a GET request to "/api/books/800"
    Then I should receive a 404 response code for retrieving books
    And the response body should contain an error message "Book not found"

  # Scenario 3: Retrieve a book without providing credentials
  Scenario: Attempt to retrieve a book without providing credentials
    When I send an unauthorized GET request to "/api/books/1"
    Then I should receive a 401 Unauthorized response code
#    And the response body should contain an error message "Authentication is required"

  # Scenario 4: Successfully retrieve a book with valid credentials
  Scenario: Successfully retrieve a book with valid credentials for admin
    Given the user is authenticated for retrieving books with username "admin" and password "password"
    When I send a GET request to "/api/books/5"
    Then I should receive a 200 response code for retrieving books
    And the response body should contain the book details:
      | id | title    | author    |
      | 5  | Book Ba  | Author Ba |


      # Scenario 5: Successfully retrieve a book with valid credentials
  Scenario: Successfully retrieve a book with valid credentials for user
    Given the user is authenticated for retrieving books with username "user" and password "password"
    When I send a GET request to "/api/books/5"
    Then I should receive a 200 response code for retrieving books
    And the response body should contain the book details:
      | id | title    | author    |
      | 5  | Book Ba  | Author Ba |


  # Scenario 6: Retrieve a book with an invalid ID format
  Scenario: Attempt to retrieve a book with an invalid ID format
    Given the user is authenticated for retrieving books with username "admin" and password "password"
    When I send a GET request to "/api/books/abc"
    Then I should receive a 400 response code for retrieving books
#    And the response body should contain an error message "Invalid ID format"

  # Scenario 7: Retrieve a book with valid user credentials but unauthorized
  Scenario: Retrieve a book with valid user credentials but unauthorized
    Given the user is authenticated for retrieving books with username "user" and password "password"
    When I send a GET request to "/api/books/1"
    Then I should receive a 401 Unauthorized response code
    And the response body should contain an error message "User is not permitted"
