package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.APIConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateBookSteps {

    private Response response;
    private Map<String, Object> requestBody;
    private String username;
    private String password;
    private static final List<Integer> createdBookIds = new ArrayList<>();

    @Given("the user is authenticated with username {string} and password {string}")
    public void the_user_is_authenticated_with_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Given("a book already exists with:")
    public void a_book_already_exists_with(DataTable dataTable) {
        Map<String, String> bookDetails = dataTable.asMaps(String.class, String.class).get(0);
        requestBody = new HashMap<>();

        if (bookDetails.get("id") != null) {
            try {
                requestBody.put("id", Integer.parseInt(bookDetails.get("id")));
            } catch (NumberFormatException e) {
                requestBody.put("id", bookDetails.get("id"));
            }
        }
        requestBody.put("title", bookDetails.get("title"));
        requestBody.put("author", bookDetails.get("author"));

        // Send a POST request to create the book first
        response = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(APIConfig.BASE_URI + "/books");

        if (response.getStatusCode() == 201 && response.jsonPath().get("id") != null) {
            createdBookIds.add(response.jsonPath().getInt("id"));
        }
    }

    @When("I send a POST request to {string} with:")
    public void i_send_a_POST_request_to_with(String endpoint, DataTable dataTable) {
        Map<String, String> bookDetails = dataTable.asMaps(String.class, String.class).get(0);
        requestBody = new HashMap<>();
        if (bookDetails.get("id") != null) {
            try {
                requestBody.put("id", Integer.parseInt(bookDetails.get("id")));
            } catch (NumberFormatException e) {
                requestBody.put("id", bookDetails.get("id"));
            }
        }

        requestBody.put("title", bookDetails.get("title"));
        requestBody.put("author", bookDetails.get("author"));

        response = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(APIConfig.BASE_URI + endpoint);

        if (response.getStatusCode() == 201 && response.jsonPath().get("id") != null) {
            createdBookIds.add(response.jsonPath().getInt("id"));
        }
    }

    @When("I send a POST request to {string} with an empty body")
    public void i_send_a_POST_request_to_with_an_empty_body(String endpoint) {
        response = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .body("") // Empty JSON body
                .post(APIConfig.BASE_URI + endpoint);
    }

    @Then("I should receive a {int} response code")
    public void i_should_receive_a_response_code(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("the response should confirm the book was created")
    public void the_response_should_confirm_the_book_was_created() {
        Assert.assertEquals(response.jsonPath().get("title"), requestBody.get("title"));
        Assert.assertEquals(response.jsonPath().get("author"), requestBody.get("author"));
    }

    @Then("the response should contain an error message {string}")
    public void the_response_should_contain_an_error_message(String expectedMessage) {
        Assert.assertEquals(response.jsonPath().getString("error"), expectedMessage);
    }

    @Then("the response should confirm the book was created with the given ID")
    public void the_response_should_confirm_the_book_was_created_with_the_given_ID() {
        Assert.assertEquals(response.jsonPath().get("id"), requestBody.get("id"));
        Assert.assertEquals(response.jsonPath().get("title"), requestBody.get("title"));
        Assert.assertEquals(response.jsonPath().get("author"), requestBody.get("author"));
    }

    @After
    public void cleanUpBooks() {
        for (Integer bookId : createdBookIds) {
            RestAssured.given()
                    .auth()
                    .basic("user", "password")
                    .delete(APIConfig.BASE_URI + "/books/" + bookId);
        }
        createdBookIds.clear();
    }

}
