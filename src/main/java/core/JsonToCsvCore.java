package core;

import java.io.IOException;
import java.util.*;

public class JsonToCsvCore {
    private static final String separatorColumn = ";";
    private static final String separatorNewLine = "\n";

    private static int numberOfLinesMax = 0;
    private static String parentKey = "";

    public static String jsonMapToCsv(LinkedHashMap<String, Object> jsonMap) throws IOException {
        if (jsonMap.isEmpty())
            return "";

        String headerLine = createHeaderLine(jsonMap);
        String bodyLines = createBodyLines(jsonMap);

        return headerLine + bodyLines;
    }

    private static String createHeaderLine(LinkedHashMap<String, Object> jsonMap) {
        return createHeader(new StringJoiner(separatorColumn), jsonMap) + separatorNewLine;
    }

    private static String createHeader(StringJoiner csvString, LinkedHashMap<String, Object> jsonMap) {
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof LinkedHashMap) {
                LinkedHashMap<String, Object> linkedHashMap = LinkedHashMap.class.cast(value);
                if (linkedHashMap.size() > 0) {
                    parentKey += key + ".";
                    createHeader(csvString, linkedHashMap);
                }
            } else {
                csvString.add(parentKey + key);
                parentKey = "";
            }
        }

        return csvString.toString();
    }

    private static String createBodyLines(LinkedHashMap<String, Object> jsonMap) {
        calculateNumberOfLines(jsonMap.values());

        StringJoiner csvString = new StringJoiner(separatorNewLine);

        for (int i = 0; i < numberOfLinesMax; i++)
            csvString.add(createBody(i, new StringJoiner(separatorColumn), jsonMap));

        numberOfLinesMax = 0; // TODO: find a better solution

        return csvString.toString();
    }

    private static String createBody(int i, StringJoiner csvString, LinkedHashMap<String, Object> jsonMap) {
        for (Object value : jsonMap.values()) {
            if (value instanceof LinkedHashMap) {
                createBody(i, csvString, LinkedHashMap.class.cast(value));
            } else if (value instanceof ArrayList) {
                ArrayList arrayList = ArrayList.class.cast(value);

                if (i < arrayList.size())
                    csvString.add(arrayList.get(i).toString());
                else
                    csvString.add("");
            } else {
                csvString.add(value.toString());
            }
        }

        return csvString.toString();
    }

    private static void calculateNumberOfLines(Collection collection) {
        int numberOfLinesCurr = 0;

        for (Object object : collection) {
            if (object instanceof ArrayList)
                numberOfLinesCurr = ArrayList.class.cast(object).size();
            else if (object instanceof LinkedHashMap)
                calculateNumberOfLines(LinkedHashMap.class.cast(object).values());
            else
                numberOfLinesCurr = 1;

            if (numberOfLinesCurr > numberOfLinesMax)
                numberOfLinesMax = numberOfLinesCurr;
        }
    }
}
