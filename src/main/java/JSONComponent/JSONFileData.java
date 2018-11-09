package JSONComponent;

import java.util.LinkedHashMap;

public class JSONFileData {
    LinkedHashMap<String, Object> map;

    public JSONFileData() {
        map = new LinkedHashMap<>();
    }

    public void add(JSONComponent component) {
        map.put(component.getKey(), component.buildToString());
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