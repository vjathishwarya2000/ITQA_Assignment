package stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.APIConfig;
import java.util.List;

public class GetAllBooksSteps {

    private Response response;

    @Given("I use invalid credentials with username {string} and password {string}")
    public void i_use_invalid_credentials_with_username_and_password(String username, String password) {
        // Configure RestAssured
        APIConfig.configureRestAssured();
        // Use invalid credentials
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @Given("the admin is authenticated with username {string} and password {string}")
    public void the_admin_is_authenticated_with_username_and_password(String username, String password) {
        // Configure RestAssured
        APIConfig.configureRestAssured();
        // Use admin credentials
        RestAssured.authentication = RestAssured.basic(username, password);
    }

    @When("I send a GET request to {string} endpoint")
    public void i_send_a_get_request_to_endpoint(String endpoint) {
        // Send GET request
        response = RestAssured.given()
                .get(APIConfig.BASE_URI + endpoint);
    }

    @Then("the response code should be {int}")
    public void the_response_code_should_be(int expectedStatusCode) {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected response code!");
    }
    @Then("I should get a {int} response code")
    public void i_should_get_a_response_code(int expectedStatusCode) {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Unexpected response code!");
    }

    @Then("the response should contain all books")
    public void the_response_should_contain_all_books() {
        Assert.assertNotNull(response, "Response is null. Ensure the request was sent.");
        List<?> books = response.jsonPath().getList("$");
        Assert.assertTrue(!books.isEmpty(), "Expected books in the response, but found none.");
    }

}
