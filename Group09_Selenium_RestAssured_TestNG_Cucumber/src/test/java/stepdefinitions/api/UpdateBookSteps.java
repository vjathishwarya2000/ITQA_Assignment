package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.APIConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateBookSteps {

    private Response response;
    private Map<String, Object> requestBody;
    private String username;
    private String password;

    @Given("the user is authenticated with username {string} and password {string} to update a book")
    public void the_user_is_authenticated_with_username_and_password_to_update_a_book(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("User authenticated using Basic Authentication with username: " + username);
    }

    @Given("a book exists with ID {int}, title {string}, and author {string}")
    public void a_book_exists_with_ID_title_and_author(int id, String title, String author) {
        Map<String, Object> bookData = new HashMap<>();
        bookData.put("id", id);
        bookData.put("title", title);
        bookData.put("author", author);

        String baseUri = APIConfig.BASE_URI;

        // Create the book if it doesn't exist
        Response createResponse = RestAssured
                .given()
                .auth()
                .basic(this.username, this.password)
                .header("Content-Type", "application/json")
                .body(bookData)
                .post(baseUri + "/books");

        System.out.println("Book creation response: " + createResponse.body().asString());

        Assert.assertTrue(createResponse.statusCode() == 201 || createResponse.statusCode() == 208,
                "Failed to create the book for the update test!");
        System.out.println("Book with ID " + id + " is ready for update tests.");
    }

    @When("I send a PUT request to {string} with updated details:")
    public void i_send_a_PUT_request_to_with_updated_details(String endpoint, DataTable dataTable) {
        List<Map<String, String>> booksList = dataTable.asMaps(String.class, String.class);
        Map<String, String> bookDetails = booksList.get(0);

        requestBody = new HashMap<>();
        if (bookDetails.get("id") != null && !bookDetails.get("id").isEmpty()) {
            requestBody.put("id", Integer.parseInt(bookDetails.get("id")));
        }
        requestBody.put("title", bookDetails.get("title").replaceAll("^\"|\"$", ""));
        requestBody.put("author", bookDetails.get("author").replaceAll("^\"|\"$", ""));

        System.out.println("PUT request body: " + requestBody);

        String baseUri = APIConfig.BASE_URI;
        String url = baseUri + endpoint + "/" + requestBody.get("id");

        response = RestAssured
                .given()
                .auth()
                .basic(this.username, this.password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put(url);

        System.out.println("PUT response: " + response.body().asString());
    }

    @Then("I should receive a {int} response code for the update operation")
    public void i_should_receive_a_response_code_for_the_update_operation(int expectedStatusCode) {
        if (response.statusCode() != expectedStatusCode) {
            System.out.println("Unexpected response body: " + response.body().asString());
        }
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code!");
    }

    @Then("the response should confirm the book was updated")
    public void the_response_should_confirm_the_book_was_updated() {
        // Validate the response
        Assert.assertEquals(response.jsonPath().get("id"), requestBody.get("id"), "Book ID mismatch in response!");
        Assert.assertEquals(response.jsonPath().get("title"), requestBody.get("title"), "Title mismatch!");
        Assert.assertEquals(response.jsonPath().get("author"), requestBody.get("author"), "Author mismatch!");

        // Print the updated details
        System.out.println("Updated Book Details:");
        System.out.println("ID: " + response.jsonPath().get("id"));
        System.out.println("Title: " + response.jsonPath().get("title"));
        System.out.println("Author: " + response.jsonPath().get("author"));
    }

    @Then("the response should indicate the book was not found")
    public void the_response_should_indicate_the_book_was_not_found() {
        Assert.assertEquals(response.statusCode(), 404, "Expected 404 for non-existent book!");

        // Print the error message
        System.out.println("Response Body: " + response.body().asString());
    }
}