import java.util.HashMap;

public class JSONObject extends JSONComponent {
    private HashMap<String, Object> table;
    private String key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String buildToString() {
        StringBuilder b = new StringBuilder(": {\n");
        for(String s: table.keySet()) {
            b.append("\t\t\""+s +"\""+ table.get(s) + ",\n");
        }

        b.append("\t}");

        return b.toString();
    }
}
