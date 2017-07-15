package wrapper;

import util.validator.InvalidJsonFormatException;
import util.validator.JsonValidator;

import java.io.File;
import java.util.LinkedHashMap;

import static core.JsonToCsvCore.jsonMapToCsv;
import static util.JsonMapReader.readJsonOriginal;

public class JsonToCsvConverter {

    public static String jsonToCsv(String json) throws InvalidJsonFormatException {
        LinkedHashMap<String, Object> jsonMap = readJsonOriginal(json);
        return validJsonMapToCsv(jsonMap);
    }

    public static String jsonToCsv(File jsonFile) throws InvalidJsonFormatException {
        LinkedHashMap<String, Object> jsonMap = readJsonOriginal(jsonFile);
        return validJsonMapToCsv(jsonMap);
    }

    private static String validJsonMapToCsv(LinkedHashMap<String, Object> jsonMap) throws InvalidJsonFormatException {
        JsonValidator.validate(jsonMap);
        return jsonMapToCsv(jsonMap);
    }
}
