package utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestDataCreator {

    public static void createInitialData() {
        // File name for the JSON data
        String jsonFileName = "initialBookData.json";

        try {
            // Use JsonUtils to read the JSON file
            JsonNode booksArray = JsonUtils.getJsonData(jsonFileName);

            // Iterate over the books array
            for (JsonNode book : booksArray) {
                // Post each book
                Response response = RestAssured.given()
                        .auth()
                        .basic("admin", "password")
                        .header("Content-Type", "application/json")
                        .body(book.toString())
                        .post(APIConfig.BASE_URI + "/books");

                if (response.getStatusCode() == 201) {
                    System.out.println("Book added successfully: " + book.get("title").asText());
                } else {
                    System.err.println("Failed to add book: " + book.get("title").asText()
                            + ". Status code: " + response.getStatusCode());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing books JSON file: " + e.getMessage(), e);
        }
    }
}
