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

    public Object getObject(String key) {
        return table.get(key);
    }

    public void add(JSONItem item) {
        table.put(item.getKey(), item.getData());
    }


    public void remove(String key) {
        table.remove(key);
    }

    public LinkedHashMap<String, Object> getData() {
        return table;
    }

    @Override
    public String buildToString() {
        String space = "  ";
        StringBuilder b = new StringBuilder(": {\n");
        int i = 0;
        for(String s: table.keySet()) {
            if(table.get(s) instanceof JSONComponent) {
                if (i < table.size() - 1) {
                    b.append(space + "\"" + s + "\" " + ((JSONComponent) table.get(s)).buildToString() + ",\n");
                } else {
                    b.append(space + "\"" + s + "\" " +((JSONComponent) table.get(s)).buildToString() + "\n");
                }
            } else if(table.get(s) instanceof  String) {
                if (i < table.size() - 1) {
                    b.append(space + "\"" + s + "\": \"" + table.get(s) + "\",\n");
                } else {
                    b.append(space + "\"" + s + "\": \"" + table.get(s) + "\"\n");
                }
            } else {
                if (i < table.size() - 1) {
                    b.append(space + "\"" + s + "\": " + table.get(s) + ",\n");
                } else {
                    b.append(space + "\"" + s + "\": " + table.get(s) + "\n");
                }
            }
            i++;
        }

        b.append(space + "}");

        return b.toString();
    }
}
