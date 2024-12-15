# Created by pavit at 12/6/2024
Feature: Books API CRUD Operations
  This feature uses separate JSON files for create and update test data.

  Scenario: Create a new book
    When I create a book using data from "books_create_request.json"
    Then the response status code should be 201

  #Scenario: Validation on fetching all books (Using GET API)
    #Given 'http://localhost:7081' is the API Base URL
    #When the GET request is sent to 'http://localhost:7081/api/books' with "admin" and password as "password"
    #Then the response status code should be 200
    #And the list of books should be in the response

