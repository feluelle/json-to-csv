package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Felix on 12.05.2017.
 */
public class JsonToCsvCore {
    private static int biggestSize = 0;

    public static String jsonMapToCsv(LinkedHashMap<String, Object> jsonMap) throws IOException {
        String headerLine = createHeaderLine(jsonMap);
        String bodyLines = createBodyLines(jsonMap);

        return headerLine + bodyLines;
    }

    private static String createHeaderLine(LinkedHashMap<String, Object> jsonMap) {
        String header = createHeader(new StringBuilder(), "", jsonMap);
        String headerLine = toLine(header);

        return headerLine;
    }

    private static String createHeader(StringBuilder stringBuilder, String jsonParentName, LinkedHashMap<String, Object> jsonMap) {
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof LinkedHashMap) {
                jsonParentName += key + ".";
                createHeader(stringBuilder, jsonParentName, LinkedHashMap.class.cast(value));
            } else {
                stringBuilder.append(jsonParentName).append(key).append(";");
            }
        }

        return stringBuilder.toString();
    }

    private static String createBodyLines(LinkedHashMap<String, Object> jsonMap) {
        findBiggestArraySize(jsonMap.values());

        StringBuilder bodyLines = new StringBuilder();

        for (int i = 0; i < biggestSize; i++) {
            String body = createBody(i, new StringBuilder(), jsonMap);
            String bodyLine = toLine(body);

            bodyLines.append(bodyLine);
        }

        biggestSize = 0; // TODO: find a better solution

        return bodyLines.toString();
    }

    private static String createBody(int i, StringBuilder stringBuilder, LinkedHashMap<String, Object> jsonMap) {
        for (Object value : jsonMap.values()) {
            if (value instanceof LinkedHashMap) {
                createBody(i, stringBuilder, LinkedHashMap.class.cast(value));
            } else if (value instanceof ArrayList) {
                ArrayList arrayList = ArrayList.class.cast(value);

                if (i < arrayList.size())
                    stringBuilder.append(arrayList.get(i));

                stringBuilder.append(";");
            } else {
                stringBuilder.append(value).append(";");
            }
        }

        return stringBuilder.toString();
    }

    private static String toLine(String csvString) {
        return csvString.substring(0, csvString.length() - 1) + "\n";
    }

    private static void findBiggestArraySize(Collection collection) {
        int size = 0;

        for (Object object : collection) {
            if (object instanceof ArrayList)
                size = ArrayList.class.cast(object).size();
            else if (object instanceof LinkedHashMap)
                findBiggestArraySize(LinkedHashMap.class.cast(object).values());
            else
                size = 1;

            if (size > biggestSize)
                biggestSize = size;
        }
    }
}