package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.APIConfig;

import java.util.HashMap;
import java.util.Map;

public class UpdateBookSteps {

    private Response response;
    private Map<String, Object> requestBody;
    private String username;
    private String password;

    @Given("the admin user is authenticated with username {string} and password {string}")
    public void the_admin_user_is_authenticated_with_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Given("a book exists in the database with ID {int}")
    public void a_book_exists_in_the_database_with_ID(int id) {
        // Check if the book exists
        response = RestAssured.given()
                .auth()
                .basic(username, password)
                .get(APIConfig.BASE_URI + "/books/" + id);

        if (response.getStatusCode() == 404) {
            Assert.fail("Book with ID " + id + " does not exist. Precondition failed.");
        }
    }

    @When("I send a PUT request to {string} with updated details:")
    public void i_send_a_PUT_request_to_with_updated_details(String endpoint, DataTable dataTable) {
        Map<String, String> bookDetails = dataTable.asMaps(String.class, String.class).get(0);
        requestBody = new HashMap<>();
        if (bookDetails.get("id") != null) {
            try {
                requestBody.put("id", Integer.parseInt(bookDetails.get("id")));
            } catch (NumberFormatException e) {
                requestBody.put("id", bookDetails.get("id"));
            }
        }
        if (bookDetails.get("title") != null) {
            requestBody.put("title", bookDetails.get("title"));
        }
        if (bookDetails.get("author") != null) {
            requestBody.put("author", bookDetails.get("author"));
        }

        response = RestAssured.given()
                .auth()
                .basic(username, password)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put(APIConfig.BASE_URI + endpoint.replace("{id}", requestBody.get("id").toString()));
    }

    @Then("I should receive a {int} response code for update")
    public void i_should_receive_a_response_code_for_update(int expectedStatusCode) {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected response code.");
    }

    @Then("the response should indicate {string}")
    public void the_response_should_indicate(String expectedMessage) {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        String responseBody = response.asString();
        Assert.assertTrue(responseBody.contains(expectedMessage),
                "Expected message not found in response. Expected: " + expectedMessage + ", but got: " + responseBody);
    }

    @Then("the response should confirm the book was updated")
    public void the_response_should_confirm_the_book_was_updated() {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        Assert.assertEquals(response.jsonPath().get("title"), requestBody.get("title"));
        Assert.assertEquals(response.jsonPath().get("author"), requestBody.get("author"));
    }

    @Then("the response should confirm the author was updated")
    public void the_response_should_confirm_the_author_was_updated() {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        Assert.assertEquals(response.jsonPath().get("author"), requestBody.get("author"));
    }
}

