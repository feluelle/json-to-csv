package wrapper;

import java.io.File;
import java.io.IOException;

import static core.JsonToCsvCore.jsonMapToCsv;

public class JsonToCsvConverter {

    public static String jsonToCsv(String json) throws IOException {
        return jsonMapToCsv(JsonMapReader.readJsonOriginal(json));
    }

    public static String jsonToCsv(File jsonFile) throws IOException {
        return jsonMapToCsv(JsonMapReader.readJsonOriginal(jsonFile));
    }

}
