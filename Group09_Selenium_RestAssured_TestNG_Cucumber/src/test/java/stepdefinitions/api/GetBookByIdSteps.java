package stepdefinitions.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import io.cucumber.java.en.*;

import java.util.Map;

public class GetBookByIdSteps {

    private Response response;
    private String username;
    private String password;

    // Step for authentication
    @Given("the user is authenticated for retrieving books with username {string} and password {string}")
    public void the_user_is_authenticated_for_retrieving_books_with_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("User authenticated for retrieving books with username: " + username);
    }

    // Step to send GET request
    @When("I send a GET request to {string}")
    public void i_send_a_GET_request_to(String endpoint) {
        String baseUri = "http://localhost:7081";
        response = RestAssured.given()
                .auth().basic(username, password) // Basic authentication
                .get(baseUri + endpoint);
        System.out.println("GET request sent to: " + baseUri + endpoint);
    }

    // Step to send unauthorized GET request
    @When("I send an unauthorized GET request to {string}")
    public void i_send_an_unauthorized_GET_request_to(String endpoint) {
        String baseUri = "http://localhost:7081";
        response = RestAssured.given()
                .get(baseUri + endpoint); // No authentication header
        System.out.println("Unauthorized GET request sent to: " + baseUri + endpoint);
    }

    // Step to validate response code
    @Then("I should receive a {int} response code for retrieving books")
    public void i_should_receive_a_response_code_for_retrieving_books(int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code!");
        System.out.println("Response code validated: " + response.statusCode());
    }

    // Step to validate unauthorized response code
    @Then("I should receive a {int} Unauthorized response code")
    public void i_should_receive_a_Unauthorized_response_code(int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code for unauthorized access!");
        System.out.println("Unauthorized response code validated: " + response.statusCode());
    }

    // Step to validate error message
    @Then("the response body should contain an error message {string}")
    public void the_response_body_should_contain_an_error_message(String expectedErrorMessage) {
        String actualErrorMessage = response.jsonPath().getString("error");
        Assert.assertNotNull(actualErrorMessage, "Error message is missing in the response!");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match!");
        System.out.println("Error message validated: " + actualErrorMessage);
    }

    // Step to validate book details
    @Then("the response body should contain the book details:")
    public void the_response_body_should_contain_the_book_details(Map<String, String> expectedDetails) {
        // Parse the response for book details
        Map<String, String> actualDetails = Map.of(
                "id", String.valueOf(response.jsonPath().getInt("id")),
                "title", response.jsonPath().getString("title"),
                "author", response.jsonPath().getString("author")
        );

        // Validate the book details
        for (String key : expectedDetails.keySet()) {
            Assert.assertEquals(actualDetails.get(key), expectedDetails.get(key), key + " does not match!");
        }

        System.out.println("Book details validated: " + actualDetails);
    }
}
