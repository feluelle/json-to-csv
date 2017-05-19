package core.json.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject implements JsonElement {

    private LinkedHashMap<String, Object> linkedHashMap;

    public JsonObject(Object object) {
        this.linkedHashMap = LinkedHashMap.class.cast(object);
    }

    public JsonObject(LinkedHashMap<String, Object> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }

    @Override
    public boolean hasElements() {
        return !linkedHashMap.isEmpty();
    }

    public Set<Map.Entry<String, Object>> getElements() {
        return linkedHashMap.entrySet();
    }

    public Collection getValues() {
        return linkedHashMap.values();
    }

    public LinkedHashMap<String, Object> get() {
        return linkedHashMap;
    }

    @Override
    public int getSize() {
        return linkedHashMap.size();
    }
}
