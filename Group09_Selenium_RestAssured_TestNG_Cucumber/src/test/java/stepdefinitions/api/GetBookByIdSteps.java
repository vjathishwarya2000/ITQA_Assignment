//package stepdefinitions.api;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import io.cucumber.java.en.*;
//
//import java.util.Map;
//
//public class GetBookByIdSteps {
//
//    private Response response;
//    private String username;
//    private String password;
//
//    @Given("the user is authenticated for retrieving books with username {string} and password {string}")
//    public void the_user_is_authenticated_for_retrieving_books_with_username_and_password(String username, String password) {
//        this.username = username;
//        this.password = password;
//        System.out.println("User authenticated for retrieving books with username: " + username);
//    }
//
//    @When("I send a GET request to {string}")
//    public void i_send_a_GET_request_to(String endpoint) {
//        String baseUri = "http://localhost:7081";
//        response = RestAssured.given()
//                .auth().basic(username, password)
//                .get(baseUri + endpoint);
//        System.out.println("GET request sent to: " + baseUri + endpoint);
//    }
//
//    @When("I send an unauthorized GET request to {string}")
//    public void i_send_an_unauthorized_GET_request_to(String endpoint) {
//        String baseUri = "http://localhost:7081";
//        response = RestAssured.given()
//                .get(baseUri + endpoint);
//        System.out.println("Unauthorized GET request sent to: " + baseUri + endpoint);
//    }
//
//    @Then("I should receive a {int} response code for retrieving books")
//    public void i_should_receive_a_response_code_for_retrieving_books(int expectedStatusCode) {
//        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code!");
//        System.out.println("Response code validated: " + response.statusCode());
//    }
//
//    @Then("I should receive a {int} Unauthorized response code")
//    public void i_should_receive_a_Unauthorized_response_code(int expectedStatusCode) {
//        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected response code for unauthorized access!");
//        System.out.println("Unauthorized response code validated: " + response.statusCode());
//    }
//
//    @Then("the response body should contain an error message {string}")
//    public void the_response_body_should_contain_an_error_message(String expectedErrorMessage) {
//        // Check if the response body exists and is not null
//        if (response.getBody() != null && !response.getBody().asString().trim().isEmpty()) {
//            String responseBody = response.getBody().asString();
//            System.out.println("Response Body: " + responseBody);
//
//            // Validate that the error message is present
//            Assert.assertTrue(responseBody.contains(expectedErrorMessage),
//                    "Expected error message not found. Expected: \"" + expectedErrorMessage + "\", Actual: \"" + responseBody + "\"");
//
//            System.out.println("Error message validated: " + expectedErrorMessage);
//        } else {
//            // If the response body is empty or null, assert based on the absence of a body
//            Assert.fail("Response body is empty or null. Expected error message: " + expectedErrorMessage);
//        }
//    }
//
//    @Then("the response body should contain the book details:")
//    public void the_response_body_should_contain_the_book_details(Map<String, String> expectedDetails) {
//        Map<String, String> actualDetails = Map.of(
//                "id", String.valueOf(response.jsonPath().getInt("id")),
//                "title", response.jsonPath().getString("title"),
//                "author", response.jsonPath().getString("author")
//        );
//
//        // Validate the book details
//        for (String key : expectedDetails.keySet()) {
//            Assert.assertEquals(actualDetails.get(key), expectedDetails.get(key), key + " does not match!");
//        }
//
//        System.out.println("Book details validated: " + actualDetails);
//    }
//}
//
//
//
package stepdefinitions.api;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

public class GetBookByIdSteps {

    private Response response;
    private String username;
    private String password;

    @Given("the user is authenticated for retrieving books with username {string} and password {string}")
    public void the_user_is_authenticated_for_retrieving_books_with_username_and_password(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("User authenticated for retrieving books with username: " + username);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_GET_request_to(String endpoint) {
        String baseUri = "http://localhost:7081";
        response = RestAssured.given()
                .auth().basic(username, password)
                .get(baseUri + endpoint);
        System.out.println("GET request sent to: " + baseUri + endpoint);
    }

    @When("I send an unauthorized GET request to {string}")
    public void i_send_an_unauthorized_GET_request_to(String endpoint) {
        String baseUri = "http://localhost:7081";
        response = RestAssured.given()
                .header("Authorization", "") // Clear any authentication header
                .get(baseUri + endpoint);
        System.out.println("Unauthorized GET request sent to: " + baseUri + endpoint);
    }

    @Then("I should receive a {int} response code for retrieving books")
    public void i_should_receive_a_response_code_for_retrieving_books(int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode,
                String.format("Unexpected response code! Expected: %d, Actual: %d", expectedStatusCode, response.statusCode()));
        System.out.println("Response code validated: " + response.statusCode());
    }

    @Then("I should receive a {int} Unauthorized response code")
    public void i_should_receive_a_Unauthorized_response_code(int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode,
                String.format("Unexpected response code for unauthorized access! Expected: %d, Actual: %d", expectedStatusCode, response.statusCode()));
        System.out.println("Unauthorized response code validated: " + response.statusCode());
    }

    @Then("the response body should contain an error message {string}")
    public void the_response_body_should_contain_an_error_message(String expectedErrorMessage) {
        String responseBody = response.getBody() != null ? response.getBody().asString().trim() : "";
        System.out.println("Response Body: " + responseBody);

        if (!responseBody.isEmpty()) {
            Assert.assertTrue(responseBody.contains(expectedErrorMessage),
                    String.format("Expected error message not found. Expected: \"%s\", Actual: \"%s\"", expectedErrorMessage, responseBody));
            System.out.println("Error message validated: " + expectedErrorMessage);
        } else {
            Assert.fail("Response body is empty or null. Expected error message: " + expectedErrorMessage);
        }
    }

    //    @Then("the response body should contain the book details:")
//    public void the_response_body_should_contain_the_book_details(DataTable dataTable) {
//        List<Map<String, String>> expectedDetails = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> expectedDetail : expectedDetails) {
//            Assert.assertEquals(String.valueOf(response.jsonPath().getInt("id")), expectedDetail.get("id"), "ID does not match!");
//            Assert.assertEquals(response.jsonPath().getString("title"), expectedDetail.get("title"), "Title does not match!");
//            Assert.assertEquals(response.jsonPath().getString("author"), expectedDetail.get("author"), "Author does not match!");
//        }
//        System.out.println("Book details validated: " + expectedDetails);
//    }
    @Then("the response body should contain the book details:")
    public void the_response_body_should_contain_the_book_details(DataTable dataTable) {
        // Convert DataTable to list of maps
        List<Map<String, String>> expectedDetails = dataTable.asMaps(String.class, String.class);

        // Extract actual details from the response
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        for (Map<String, String> expectedDetail : expectedDetails) {
            Assert.assertEquals(response.jsonPath().getString("id"), expectedDetail.get("id"), "ID does not match!");
            Assert.assertEquals(response.jsonPath().getString("title"), expectedDetail.get("title"), "Title does not match!");
            Assert.assertEquals(response.jsonPath().getString("author"), expectedDetail.get("author"), "Author does not match!");
        }

        System.out.println("Book details validated successfully.");
    }

}
