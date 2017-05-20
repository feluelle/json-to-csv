package core.json.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject implements JsonElement {
    private LinkedHashMap<String, Object> linkedHashMap;

    public JsonObject(Object object) {
        linkedHashMap = LinkedHashMap.class.cast(object);
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

    public Collection<Object> getValues() {
        return linkedHashMap.values();
    }

    @Override
    public int getSize() {
        return linkedHashMap.size();
    }
}
