import java.util.HashMap;

class JSONFileData {
    HashMap<String, Object> map;

    public JSONFileData() {
        map = new HashMap<>();
    }

    public void addItem(JSONItem item) {
        map.put(item.getKey(), item.buildToString());
    }

    public void addArray(JSONArray array) {
        map.put(array.getKey(), array.buildToString());
    }

    public void remove(String key) {
        map.remove(key);
    }

    public String buildToString() {
        StringBuilder b = new StringBuilder();
        for(String s: map.keySet()) {
            b.append("\t" + s + map.get(s) + ",\n");

        }

        return b.toString();
    }
}