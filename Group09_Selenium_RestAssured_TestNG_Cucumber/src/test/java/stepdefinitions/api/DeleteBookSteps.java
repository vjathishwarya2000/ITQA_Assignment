package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.APIConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import java.util.Map;

public class DeleteBookSteps {

    private static final Logger logger = LoggerFactory.getLogger(DeleteBookSteps.class);
    private Response response;
    private String username;
    private String password;

    @Given("the user is authenticated with username {string} and password {string} to delete a book")
    public void the_user_is_authenticated_as_an_admin_with_username_and_password_to_delete_a_book(String username, String password) {
        this.username = username;
        this.password = password;
        logger.info("User authenticated using Basic Authentication with username: {}", username);
    }

    @Given("the user is not authenticated")
    public void the_user_is_not_authenticated() {
        this.username = null;
        this.password = null;
        logger.info("User is not authenticated.");
    }

    @When("I send a DELETE request to {string} with:")
    public void i_send_a_DELETE_request_to_with(String endpoint, DataTable dataTable) {
        // Parse single-column DataTable as a list
        List<String> rows = dataTable.asList(String.class);
        logger.info("Parsed DataTable as list: {}", rows);

        // Validate and extract the book ID
        if (rows.isEmpty() || rows.size() < 2 || rows.get(1).isEmpty()) {
            throw new IllegalArgumentException("Book ID is missing or improperly formatted!");
        }

        String bookId = rows.get(1).trim(); // Extract the second row (ID value)
        logger.info("Preparing to delete book with ID: {}", bookId);

        // Send DELETE request with or without authentication
        String baseUri = APIConfig.BASE_URI;
        if (this.username != null && this.password != null) {
            // Use authentication
            response = RestAssured
                    .given()
                    .auth()
                    .basic(this.username, this.password) // Use stored credentials
                    .delete(baseUri + endpoint.replace("{id}", bookId));
        } else {
            // Unauthenticated request
            response = RestAssured
                    .given()
                    .delete(baseUri + endpoint.replace("{id}", bookId));
        }

        logger.info("DELETE request sent to endpoint: {}", baseUri + endpoint.replace("{id}", bookId));
    }

    @Then("I should receive a {int} delete response code")
    public void i_should_receive_a_delete_response_code(int expectedStatusCode) {
        // Assert the response status code
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code!");
        logger.info("Received expected response code: {}", expectedStatusCode);
    }

    @Then("the response should confirm the book was deleted")
    public void the_response_should_confirm_the_book_was_deleted() {
        // Validate the response
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Book deleted successfully", "Unexpected deletion confirmation message!");
        logger.info("Deletion confirmed with message: {}", message);
    }

    @Then("the response should confirm the book was not found")
    public void the_response_should_confirm_the_book_was_not_found() {
        // Log the raw response
        String responseBody = response.getBody().asString();
        logger.info("Response Body: {}", responseBody);

        // Check if the response is valid JSON
        try {
            String errorMessage = response.jsonPath().getString("error");

            // Validate the JSON error message
            Assert.assertNotNull(errorMessage, "Error message is missing in the response!");
            Assert.assertTrue(errorMessage.contains("not found"), "Error message does not indicate 'not found'.");
            logger.info("Error message validation successful: {}", errorMessage);
        } catch (Exception e) {
            // Fallback for plain text response
            Assert.assertTrue(responseBody.contains("not found"), "Response does not indicate 'not found'.");
            logger.info("Error validation successful for plain text response.");
        }
    }

    @Then("the response should confirm the lack of authorization")
    public void the_response_should_confirm_the_lack_of_authorization() {
        // Log the raw response body
        String responseBody = response.getBody().asString();
        logger.info("Response Body: {}", responseBody);

        // Validate status code for lack of authorization
        Assert.assertEquals(response.getStatusCode(), 401, "Expected 401 Unauthorized status code.");

        // Optionally validate the response body if it is not empty
        if (responseBody != null && !responseBody.isEmpty()) {
            try {
                String errorMessage = response.jsonPath().getString("error");

                // Validate JSON error message
                Assert.assertNotNull(errorMessage, "Error message is missing in the response!");
                Assert.assertTrue(errorMessage.contains("Unauthorized"), "Error message does not indicate 'Unauthorized'.");
                logger.info("Error message validation successful: {}", errorMessage);
            } catch (Exception e) {
                // Fallback for plain text response
                Assert.assertTrue(responseBody.contains("Unauthorized"), "Response does not indicate 'Unauthorized'.");
                logger.info("Error validation successful for plain text response.");
            }
        } else {
            logger.info("Authorization failure validated with status code only (empty response body).");
        }
    }
}
