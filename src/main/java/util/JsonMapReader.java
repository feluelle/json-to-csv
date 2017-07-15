package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import util.validator.InvalidJsonFormatException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JsonMapReader {
    /**
     * Converts the input json into a LinkedHashMap.
     * Note: This means the hashmap's order will be the same as the json input's order.
     *
     * @param json The input json to the read the value from
     * @return The converted LinkedHashMap
     * @throws InvalidJsonFormatException
     */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> readJsonOriginal(String json) throws InvalidJsonFormatException {
        return readJson(json, LinkedHashMap.class);
    }

    /**
     * Converts the input json into a Hashmap.
     * Note: If you are going for performance this might be better to use instead of readJsonOriginal.
     *
     * @param json The input json to the read the value from
     * @return The converted Hashmap
     * @throws InvalidJsonFormatException
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> readJsonRandom(String json) throws InvalidJsonFormatException {
        return readJson(json, HashMap.class);
    }

    /**
     * Converts the input file json into a LinkedHashMap.
     * Note: This means the hashmap's order will be the same as the json input's order.
     *
     * @param jsonFile The input json file to the read the value from
     * @return The converted LinkedHashMap
     * @throws InvalidJsonFormatException
     */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String, Object> readJsonOriginal(File jsonFile) throws InvalidJsonFormatException {
        return readJsonFile(jsonFile, LinkedHashMap.class);
    }

    /**
     * Converts the input file json into a Hashmap.
     * Note: If you are going for performance this might be better to use instead of readJsonOriginal.
     *
     * @param jsonFile The input json file to the read the value from
     * @return The converted Hashmap
     * @throws InvalidJsonFormatException
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> readJsonRandom(File jsonFile) throws InvalidJsonFormatException {
        return readJsonFile(jsonFile, HashMap.class);
    }

    private static <T> T readJson(String json, Class<T> clazz) throws InvalidJsonFormatException {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new InvalidJsonFormatException(e);
        }
    }

    private static <T> T readJsonFile(File jsonFile, Class<T> clazz) throws InvalidJsonFormatException {
        try {
            return new ObjectMapper().readValue(jsonFile, clazz);
        } catch (IOException e) {
            throw new InvalidJsonFormatException(e);
        }
    }
}
