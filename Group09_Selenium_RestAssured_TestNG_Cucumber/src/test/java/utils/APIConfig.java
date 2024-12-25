package utils;

import io.restassured.RestAssured;

public class APIConfig {
    // Base URI for the API
    public static final String BASE_URI = "http://localhost:7081/api";

    // Common setup method for Rest Assured
    public static void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
    }
}
