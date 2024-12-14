package stepdefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.Config;
import utils.JsonUtils;

import java.io.IOException;

public class Steps {
    private Response response;

    @When("I create a book using data from {string}")
    public void createNewBookUsingDataFrom(String fileName) throws IOException {
        // Read JSON data for creating a book
        JsonNode jsonData = JsonUtils.getJsonData(fileName);
        String requestBody = jsonData.toString();

        // Send POST request with Basic Authentication
        response = RestAssured
                .given()
                .auth()
                .basic("admin", "password")
                .contentType("application/json")
                .body(requestBody)
                .post("/api/books");
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Validate the status code
        response.then().statusCode(expectedStatusCode);
    }

}
