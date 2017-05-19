package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JsonMapReader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static LinkedHashMap<String, Object> readJsonOriginal(String json) throws IOException {
        return objectMapper.readValue(json, LinkedHashMap.class);
    }

    public static HashMap<String, Object> readJsonRandom(String json) throws IOException {
        return objectMapper.readValue(json, HashMap.class);
    }

    public static LinkedHashMap<String, Object> readJsonOriginal(File jsonFile) throws IOException {
        return objectMapper.readValue(jsonFile, LinkedHashMap.class);
    }

    public static HashMap<String, Object> readJsonRandom(File jsonFile) throws IOException {
        return objectMapper.readValue(jsonFile, HashMap.class);
    }
}
