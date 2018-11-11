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

    public void addAndCreateJSONArrayComponent(String key, List<JSONItem> list) {
        this.list.add(createJSONArrayComponent(key,list));
    }

    public void addAndCreateJSONArrayComponent(String key, JSONItem item) {
        this.list.add(createJSONArrayComponent(key,item));
    }

    public String buildToString() {
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
            builder.append("\t]");
        } else {
            builder.append(": []");
        }


        return builder.toString();
    }

    public JSONArrayComponent createJSONArrayComponent(String key, List<JSONItem> itemList) {
        JSONArrayComponent component = new JSONArrayComponent(key);

        itemList.forEach((JSONItem item) -> component.add(item));

        return component;
    }

    public JSONArrayComponent createJSONArrayComponent(String key, JSONItem item) {
        JSONArrayComponent component = new JSONArrayComponent(key);
        component.add(item);
        return component;
    }

    public class JSONArrayComponent extends JSONComponent{
        private LinkedHashMap<String, Object> map;

        public JSONArrayComponent(String key) {
            setKey(key);
            map = new LinkedHashMap<>();
        }

        public void add(JSONItem item) {
            map.put(item.getKey(), item.buildToString());
        }

        public void remove(String key) {
            map.remove(key);
        }

        public String buildToString() {
            String space = "    ";
            StringBuilder builder = new StringBuilder(space + space+"{\n");
            int i = 0;
            for(String s: map.keySet()) {
                if(i < map.size()-1) {
                    builder.append(space + space + "  " + s + "\" " + map.get(s) + ",\n");
                } else  {
                    builder.append(space + space  + "  " + s + "\" " + map.get(s) + "\n");
                }
                i++;
            }

            builder.append(space + space +"}");

            return builder.toString();
        }
    }
}


