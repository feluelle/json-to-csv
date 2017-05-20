package core.json.model;

public class JsonPrimitive implements JsonElement {
    private Object object;

    public JsonPrimitive(Object object) {
        this.object = object;
    }

    @Override
    public boolean hasElements() {
        return false;
    }

    @Override
    public int getSize() {
        return 1;
    }
}
