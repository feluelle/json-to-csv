package util;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JsonHelper {
    public enum JsonType {
        Object,
        Array,
        Other /* Number, String, Boolean */
    }

    public static JsonType getJsonType(Object object) {
        if (object instanceof LinkedHashMap)
            return JsonType.Object;
        if (object instanceof ArrayList)
            return JsonType.Array;

        return JsonType.Other;
    }
}
