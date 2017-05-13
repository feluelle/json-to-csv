package wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

class JsonMapReader {

    static LinkedHashMap<String, Object> readJsonOriginal(String json) throws IOException {
        return new ObjectMapper().readValue(json, LinkedHashMap.class);
    }

    static HashMap<String, Object> readJsonRandom(String json) throws IOException {
        return new ObjectMapper().readValue(json, HashMap.class);
    }

    static LinkedHashMap<String, Object> readJsonOriginal(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(jsonFile, LinkedHashMap.class);
    }

    static HashMap<String, Object> readJsonRandom(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(jsonFile, HashMap.class);
    }

}
