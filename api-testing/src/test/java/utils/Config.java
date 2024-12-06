package utils;

import io.restassured.RestAssured;

public class Config {
    // Base URI for the API
    public static final String BASE_URI = "http://localhost:7081";

    // Common setup method for Rest Assured
    public static void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
    }
}
