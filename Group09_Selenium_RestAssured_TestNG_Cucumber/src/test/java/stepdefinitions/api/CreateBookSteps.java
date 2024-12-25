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

public class CreateBookSteps {

    private Response response;
    private Map<String, Object> requestBody;
    private String username;
    private String password;

    @Given("the user is authenticated with username {string} and password {string}")
    public void the_user_is_authenticated_as_an_admin_with_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("User authenticated using Basic Authentication with username: " + username);
    }

    @When("I send a POST request to {string} with:")
    public void i_send_a_POST_request_to_with(String endpoint, DataTable dataTable) {
        // Convert DataTable to List of Maps
        List<Map<String, String>> booksList = dataTable.asMaps(String.class, String.class);

        // Assuming there's only one row of data for simplicity
        Map<String, String> bookDetails = booksList.get(0);

        // Prepare the request body
        requestBody = new HashMap<>();
        if (bookDetails.get("id") != null && !bookDetails.get("id").isEmpty()) {
            requestBody.put("id", Integer.parseInt(bookDetails.get("id")));
        }

        requestBody.put("title", bookDetails.get("title").replaceAll("^\"|\"$", ""));
        requestBody.put("author", bookDetails.get("author").replaceAll("^\"|\"$", ""));

        System.out.println(requestBody);

        // Send POST request with Basic Authentication
        String baseUri = APIConfig.BASE_URI;
        response = RestAssured
                .given()
                .auth()
                .basic(this.username, this.password) // Use stored credentials
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(baseUri + endpoint);
    }

    @Then("I should receive a {int} response code")
    public void i_should_receive_a_response_code(int expectedStatusCode) {
        // Assert the response status code
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code!");
    }

    @Then("the response should confirm the book was created")
    public void the_response_should_confirm_the_book_was_created() {
        // Validate the response
        Assert.assertNotNull(response.jsonPath().get("id"), "Book ID is missing in response!");
        Assert.assertEquals(response.jsonPath().get("title"), requestBody.get("title"), "Title mismatch!");
        Assert.assertEquals(response.jsonPath().get("author"), requestBody.get("author"), "Author mismatch!");
    }

}
