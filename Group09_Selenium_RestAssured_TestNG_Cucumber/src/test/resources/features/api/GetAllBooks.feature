@get-all-books @api
Feature: Fetching all books

  Scenario: Accessing getAllBooks API with invalid credentials
    Given I use invalid credentials with username "invalidUser" and password "invalidPass"
    When I send a GET request to "/books" endpoint
    Then the response code should be 401


