package utils;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;

public class APIConfig {
    public static final String BASE_URI = "http://localhost:7081/api";

    public static void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.socket.timeout", 5000));
    }
}
