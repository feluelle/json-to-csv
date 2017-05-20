package core.json.model;

import java.util.ArrayList;

public class JsonArray implements JsonElement {
    private ArrayList arrayList;

    public JsonArray(Object object) {
        arrayList = ArrayList.class.cast(object);
    }

    @Override
    public boolean hasElements() {
        return !arrayList.isEmpty();
    }

    public Object getFirstElement() {
        return hasElements() ? arrayList.get(0) : null;
    }

    public Object getElement(int index) {
        return arrayList.get(index);
    }

    @Override
    public int getSize() {
        return arrayList.size();
    }
}
