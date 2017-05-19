package wrapper;

import java.io.File;
import java.io.IOException;

import static core.JsonToCsvCore.jsonMapToCsv;
import static util.JsonMapReader.readJsonOriginal;

public class JsonToCsvConverter {

    public static String jsonToCsv(String json) throws IOException {
        return jsonMapToCsv(readJsonOriginal(json));
    }

    public static String jsonToCsv(File jsonFile) throws IOException {
        return jsonMapToCsv(readJsonOriginal(jsonFile));
    }

}
