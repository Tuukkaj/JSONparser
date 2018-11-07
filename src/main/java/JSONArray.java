import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONArray extends JSONComponent{
    ArrayList<JSONArrayComponent> list;

    public JSONArray(String key) {
        setKey(key);
        list = new ArrayList<>();
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
        StringBuilder builder = new StringBuilder( ": [\n");

        JSONArrayComponent lastComponent = list.get(list.size()-1);
        list.forEach((JSONArrayComponent) -> {
            if(lastComponent.equals(JSONArrayComponent)) {
                builder.append(JSONArrayComponent.buildToString()+"\n");
            } else {
                builder.append(JSONArrayComponent.buildToString() + ",\n");

            }
        });
        builder.append("\t]");

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
        private HashMap<String, Object> map;

        public JSONArrayComponent() {
            map = new HashMap<>();
        }
        public JSONArrayComponent(String key) {
            setKey(key);
            map = new HashMap<>();
        }

        public void add(JSONItem item) {
            map.put(item.getKey(), item.buildToString());
        }

        public void remove(String key) {
            map.remove(key);
        }

        public String buildToString() {
            StringBuilder builder = new StringBuilder("\t{\n");
            for(String s: map.keySet()) {
                builder.append("\t\t\"" + s + "\" " + map.get(s) + ",\n");
            }

            builder.append("\t}");

            return builder.toString();
        }
    }
}


