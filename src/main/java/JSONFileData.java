import java.util.HashMap;

class JSONFileData {
    HashMap<String, Object> map;

    public JSONFileData() {
        map = new HashMap<>();
    }

    public void addItem(JSONObject data) {
        map.put(data.getKey(), data.getData());
    }

    public void remove(String key) {
        map.remove(key);
    }

    public String buildToString() {
        StringBuilder b = new StringBuilder();

        for(String s: map.keySet()) {
            b.append("\t" + s + " : " + map.get(s) + ",\n");

        }

        return b.toString();
    }
}