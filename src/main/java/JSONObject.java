import java.util.HashMap;

public class JSONObject extends JSONComponent {
    private HashMap<String, Object> table;

    public JSONObject() {
        table = new HashMap<>();
    }

    public JSONObject(String key) {
        table = new HashMap<>();
        setKey(key);
    }

    public void add(JSONItem item) {
        table.put(item.getKey(), item.buildToString());
    }

    public void add(String key, Object data) {
        table.put(key,data);
    }

    public void remove(String key) {
        table.remove(key);
    }

    public HashMap<String, Object> getData() {
        return table;
    }

    @Override
    public String buildToString() {
        StringBuilder b = new StringBuilder(": {\n");
        int i = 0;
        for(String s: table.keySet()) {
            if(i < table.size()-1) {
                b.append("\t\t\"" + s + "\"" + table.get(s) + ",\n");
            } else {
                b.append("\t\t\"" + s + "\"" + table.get(s) + "\n");
            }
            i++;
        }

        b.append("\t}");

        return b.toString();
    }
}
