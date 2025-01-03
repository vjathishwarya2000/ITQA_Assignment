@get-all-books @api
Feature: Fetching all books

  Scenario: Accessing getAllBooks API with invalid credentials
    Given I use invalid credentials with username "invalidUser" and password "invalidPass"
    When I send a GET request to "/books" endpoint
    Then the response code should be 401

  Scenario: Fetch all books successfully
    Given the admin is authenticated with username "admin" and password "password"
    When I send a GET request to "/books" endpoint
    Then I should get a 200 response code
    And the response should contain all books
