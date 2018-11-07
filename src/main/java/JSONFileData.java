import java.util.HashMap;

class JSONFileData {
    HashMap<String, Object> map;

    public JSONFileData() {
        map = new HashMap<>();
    }

    public void addItem(JSONItem item) {
        map.put(item.getKey(), item.buildToString());
    }

    public void addObject(JSONObject object) {
        map.put(object.getKey(), object.buildToString());
    }

    public void addArray(JSONArray array) {
        map.put(array.getKey(), array.buildToString());
    }

    public void remove(String key) {
        map.remove(key);
    }

    public String buildToString() {
        StringBuilder b = new StringBuilder();
        int i = 0;

        for(String s: map.keySet()) {
            if (i >= map.size()-1) {
                b.append("\t\"" + s +"\""+ map.get(s) + "\n");
            } else {
                b.append("\t\"" + s +"\""+ map.get(s) + ",\n");
            }
            i++;
        }

        return b.toString();
    }
}