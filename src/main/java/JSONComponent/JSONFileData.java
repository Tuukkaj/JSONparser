package JSONComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class JSONFileData {
    LinkedHashMap<String, JSONComponent> map;

    public JSONFileData() {
        map = new LinkedHashMap<>();
    }

    public ArrayList<JSONComponent> getComponents() {
        ArrayList<JSONComponent> componentList = new ArrayList<>();
        for(String s: map.keySet()) {
            componentList.add(map.get(s));
        }
        return componentList;
    }

    public JSONComponent getComponent(String key) {
        return map.get(key);
    }

    public void add(JSONComponent component) {
        map.put(component.getKey(), component);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public String buildToString() {
        String space = "  ";
        StringBuilder b = new StringBuilder("{\n");
        int i = 0;

        for(String s: map.keySet()) {
            if (i >= map.size()-1) {
                b.append("\"" + s +"\""+ map.get(s).buildToString() + "\n");
            } else {
                b.append("\""+ s +"\""+ map.get(s).buildToString() + ",\n");
            }
            i++;
        }

        b.append("}\n");

        return indent(b.toString());
    }

    private String indent(String textFile) {
        int indentLevel = 0;
        String space = "  ";
        List<String> list = new ArrayList<>(Arrays.asList(textFile.split("\n")));
        StringBuilder b = new StringBuilder();

        for(String s: list) {
            if(s.endsWith("},") | s.endsWith("}") | s.endsWith("],") | s.endsWith("]")) {
                indentLevel--;
            }

            for(int i = 0; i < indentLevel; i++) {
                b.append(space);
            }

            if(s.endsWith("{,") | s.endsWith("{") | s.endsWith("[") | s.endsWith("[,")) {
                indentLevel++;
            }

            b.append(s+"\n");
        }

        return b.toString();
    }
}