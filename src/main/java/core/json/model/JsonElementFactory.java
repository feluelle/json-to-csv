package core.json.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JsonElementFactory {

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

    public JsonElement getJsonElement(Object object) {
        switch (getJsonType(object)) {
            case Object:
                return new JsonObject(object);
            case Array:
                return new JsonArray(object);
            case Other:
            default:
                return new JsonPrimitive(object);
        }
    }

}
