package JSONComponent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class JSONArray extends JSONComponent{
    ArrayList<JSONArrayComponent> list;

    public JSONArray(String key) {
        setKey(key);
        list = new ArrayList<>();
    }

    public ArrayList<JSONArrayComponent> getData() {
        return list;
    }

    public void add(JSONArrayComponent component) {
            list.add(component);
    }

    public void addAndCreateJSONArrayComponent(List<JSONItem> list) {
        this.list.add(createJSONArrayComponent(list));
    }

    public void addAndCreateJSONArrayComponent(JSONItem item) {
        this.list.add(createJSONArrayComponent(item));
    }

    public String buildToString() {
        String space = "  ";
        StringBuilder builder = new StringBuilder();
        if (list.size() > 0) {
            builder.append(": [\n");
            JSONArrayComponent lastComponent = list.get(list.size() - 1);
            list.forEach((JSONArrayComponent) -> {
                if (lastComponent.equals(JSONArrayComponent)) {
                    builder.append(JSONArrayComponent.buildToString() + "\n");
                } else {
                    builder.append(JSONArrayComponent.buildToString() + ",\n");

                }
            });
            builder.append("]");
        } else {
            builder.append(": []");
        }


        return builder.toString();
    }

    public JSONArrayComponent createJSONArrayComponent(List<JSONItem> itemList) {
        JSONArrayComponent component = new JSONArrayComponent();

        itemList.forEach((JSONItem item) -> component.add(item));

        return component;
    }

    public JSONArrayComponent createJSONArrayComponent(JSONItem item) {
        JSONArrayComponent component = new JSONArrayComponent();
        component.add(item);
        return component;
    }

    public class JSONArrayComponent extends JSONComponent{
        private LinkedHashMap<String, Object> map;

        public JSONArrayComponent() {
            map = new LinkedHashMap<>();
        }

        public void add(JSONItem item) {
            map.put(item.getKey(), item.getData());
        }

        public void remove(String key) {
            map.remove(key);
        }

        public String buildToString() {
            String space = "  ";
            StringBuilder builder = new StringBuilder("{\n");
            int i = 0;
            for(String s: map.keySet()) {
                if(map.get(s) instanceof JSONComponent) {
                    if (i < map.size() - 1) {
                        builder.append("\"" + s + "\" " + ((JSONComponent) map.get(s)).buildToString() + ",\n");
                    } else {
                        builder.append("\"" + s + "\" " + ((JSONComponent) map.get(s)).buildToString() + "\n");
                    }
                } else if(map.get(s) instanceof String) {
                    if (i < map.size() - 1) {
                        builder.append("\"" + s + "\": \"" + map.get(s) + "\",\n");
                    } else {
                        builder.append("\"" + s + "\": \"" + map.get(s) + "\"\n");
                    }
                } else {
                    if (i < map.size() - 1) {
                        builder.append("\"" + s + "\": " + map.get(s) + ",\n");
                    } else {
                        builder.append("\"" + s + "\": " + map.get(s) + "\n");
                    }
                }
                i++;
            }

            builder.append("}");

            return builder.toString();
        }
    }
}


