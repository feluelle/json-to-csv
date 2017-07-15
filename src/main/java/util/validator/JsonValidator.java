package util.validator;

import core.json.model.JsonArray;
import core.json.model.JsonObject;
import util.JsonHelper;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

public class JsonValidator {
    public static void validate(LinkedHashMap<String, Object> jsonMap) throws InvalidJsonFormatException {
        JsonObject jsonObject = new JsonObject(jsonMap);

        validateForArrayInsideAnotherArray(jsonObject);
        validateForDifferentObjectNamesInArray(jsonObject);
    }

    private static void validateForDifferentObjectNamesInArray(JsonObject jsonObject) throws InvalidJsonFormatException {
        // TODO
    }

    private static void validateForArrayInsideAnotherArray(JsonObject jsonObject) throws InvalidJsonFormatException {
        // TODO: Recursively

        jsonObject.getValues().stream()
                .filter(jsonArrays())
                .map(JsonArray::new)
                .flatMap(jsonArray -> jsonArray.getElements().stream())
                .filter(jsonArrays())
                .findAny()
                .ifPresent(arrayList -> {
                    throw new InvalidJsonFormatException("Error: There is an Array inside another Array.");
                });
    }

    private static Predicate<Object> jsonArrays() {
        return o -> JsonHelper.getJsonType(o) == JsonHelper.JsonType.Array;
    }
}
