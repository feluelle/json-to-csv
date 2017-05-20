package core.json.model;

import static util.JsonHelper.getJsonType;

public class JsonElementFactory {
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
