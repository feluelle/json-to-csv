package core;

import core.json.model.JsonArray;
import core.json.model.JsonElementFactory;
import core.json.model.JsonObject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static core.csv.model.CsvFinals.separatorColumn;
import static core.csv.model.CsvFinals.separatorKey;
import static core.csv.model.CsvFinals.separatorNewLine;
import static core.json.model.JsonElementFactory.getJsonType;

public class JsonToCsvCore {
    public static String jsonMapToCsv(LinkedHashMap<String, Object> jsonMap) throws IOException {
        JsonObject jsonObject = new JsonObject(jsonMap);

        String headerLine = createHeaderLine(jsonObject);

        return jsonObject.hasElements() ? headerLine + createBodyLines(jsonObject) : "";
    }

    private static String createHeaderLine(JsonObject jsonObject) {
        return jsonObject.getElements().stream()
                .map(e -> createHeaderField(e.getKey(), e.getValue()))
                .collect(Collectors.joining(separatorColumn)) + separatorNewLine;
    }

    private static String createHeaderField(String key, Object value) {
        switch (getJsonType(value)) {
            case Object:
                JsonObject jsonObject = new JsonObject(value);
                if (jsonObject.hasElements())
                    key += separatorKey + jsonObject.getElements().stream()
                            .map(e -> createHeaderField(e.getKey(), e.getValue()))
                            .collect(Collectors.joining(separatorColumn));
                break;
            case Array:
                JsonArray jsonArray = new JsonArray(value);
                if (jsonArray.hasElements())
                    key = createHeaderField(key, jsonArray.getFirstElement());
                break;
            case Other:
            default:
                break;
        }

        return key;
    }

    private static String createBodyLines(JsonObject jsonObject) {
        StringJoiner lines = new StringJoiner(separatorNewLine);

        for (int i = 0; i < getMaxNumberOfLines(jsonObject); i++)
            lines.add(createBodyFields(i, jsonObject));

        return lines.toString();
    }

    private static String createBodyFields(int index, JsonObject jsonObject) {
        StringJoiner fields = new StringJoiner(separatorColumn);

        for (Object value : jsonObject.getValues())
            switch (getJsonType(value)) {
                case Object:
                    fields.add(createBodyFields(index, new JsonObject(value)));
                    break;
                case Array:
                    JsonArray jsonArray = new JsonArray(value);

                    if (index < jsonArray.getSize()) {
                        Object object = jsonArray.getElement(index);
                        switch (getJsonType(object)) {
                            case Object:
                                fields.add(createBodyFields(index, new JsonObject(object)));
                                break;
                            case Array:
                            case Other:
                            default:
                                fields.add(object.toString());
                                break;
                        }
                    } else
                        fields.add("");
                    break;
                case Other:
                default:
                    fields.add(value.toString());
                    break;
            }

        return fields.toString();
    }

    private static int getMaxNumberOfLines(JsonObject jsonObject) {
        int numberOfLinesMax = 0;

        for (Object object : jsonObject.getValues()) {
            int numberOfLinesCurr = getNumberOfLines(object);

            if (numberOfLinesCurr > numberOfLinesMax)
                numberOfLinesMax = numberOfLinesCurr;
        }

        return numberOfLinesMax;
    }

    private static int getNumberOfLines(Object object) {
        return new JsonElementFactory().getJsonElement(object).getSize();
    }
}
