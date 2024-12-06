# Created by pavit at 12/6/2024
Feature: Books API CRUD Operations
  This feature uses separate JSON files for create and update test data.

  Scenario: Create a new book
    When I create a book using data from "books_create_request.json"
    Then the response status code should be 201
