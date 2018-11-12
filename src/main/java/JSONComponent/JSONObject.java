package JSONComponent;

import java.util.LinkedHashMap;

public class JSONObject extends JSONComponent {
    private LinkedHashMap<String, Object> table;

    public JSONObject() {
        table = new LinkedHashMap<>();
    }

    public JSONObject(String key) {
        table = new LinkedHashMap<>();
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

    public LinkedHashMap<String, Object> getData() {
        return table;
    }

    @Override
    public String buildToString() {
        String space = "    ";
        StringBuilder b = new StringBuilder(": {\n");
        int i = 0;
        for(String s: table.keySet()) {
            if(i < table.size()-1) {
                b.append(space + space +"\""+ s + "\": " + table.get(s) + ",\n");
            } else {
                b.append(space + space + "\""+ s + "\": " + table.get(s) + "\n");
            }
            i++;
        }

        b.append(space + "}");

        return b.toString();
    }
}
