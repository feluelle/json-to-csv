package core;

import core.json.model.JsonArray;
import core.json.model.JsonElementFactory;
import core.json.model.JsonObject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static core.csv.model.CsvFinals.separatorColumn;
import static core.csv.model.CsvFinals.separatorKey;
import static core.csv.model.CsvFinals.separatorNewLine;
import static util.JsonHelper.getJsonType;

public class JsonToCsvCore {
    private final static JsonElementFactory jsonElementFactory = new JsonElementFactory();

    public static String jsonMapToCsv(LinkedHashMap<String, Object> jsonMap) throws IOException {
        JsonObject jsonObject = new JsonObject(jsonMap);

        return jsonObject.hasElements() ? createHeaderLine(jsonObject) + createBodyLines(jsonObject) : "";
    }

    private static String createHeaderLine(JsonObject jsonObject) {
        return jsonObject.getElements().stream()
                .map(e -> createHeaderField(e.getKey(), e.getValue()))
                .collect(Collectors.joining(separatorColumn)) + separatorNewLine;
    }

    private static String createHeaderFields(String parentKey, JsonObject jsonObject) {
        return jsonObject.getElements().stream()
                .map(object -> parentKey + separatorKey + createHeaderField(object.getKey(), object.getValue()))
                .collect(Collectors.joining(separatorColumn));
    }

    private static String createHeaderField(String key, Object value) {
        switch (getJsonType(value)) {
            case Object:
                JsonObject jsonObject = new JsonObject(value);
                return jsonObject.hasElements() ? createHeaderFields(key, jsonObject) : key;
            case Array:
                JsonArray jsonArray = new JsonArray(value);
                return jsonArray.hasElements() ? createHeaderField(key, jsonArray.getFirstElement()) : key;
            case Other:
            default:
                return key;
        }
    }

    private static String createBodyLines(JsonObject jsonObject) {
        return IntStream.range(0, getMaxNumberOfLines(jsonObject))
                .mapToObj(index -> createBodyFields(index, jsonObject))
                .collect(Collectors.joining(separatorNewLine));
    }

    private static String createBodyFields(int index, JsonObject jsonObject) {
        return jsonObject.getValues().stream()
                .map(object -> createBodyField(index, object))
                .collect(Collectors.joining(separatorColumn));
    }

    private static String createBodyField(int index, Object object) {
        switch (getJsonType(object)) {
            case Object:
                return createBodyFields(index, new JsonObject(object));
            case Array:
                JsonArray jsonArray = new JsonArray(object);
                return index < jsonArray.getSize() ? createBodyField(index, jsonArray.getElement(index)) : "";
            case Other:
            default:
                return object.toString();
        }
    }

    private static int getMaxNumberOfLines(JsonObject jsonObject) {
        return jsonObject.getValues().stream()
                .mapToInt(JsonToCsvCore::getNumberOfLines)
                .max()
                .getAsInt();
    }

    private static int getNumberOfLines(Object object) {
        return jsonElementFactory.getJsonElement(object).getSize();
    }
}
