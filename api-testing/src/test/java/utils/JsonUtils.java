package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static final String TEST_DATA_PATH = "src/test/resources/testdata/";

    // Generic method to read JSON data
    public static JsonNode getJsonData(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(new File(TEST_DATA_PATH + fileName));
    }
}
