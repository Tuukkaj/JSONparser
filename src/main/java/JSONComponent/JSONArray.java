package JSONComponent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * JSONArray can store JSONItem's, JSONObject's and other JSONArray's. Used by JSONFileData.
 * Holds inner class JSONArrayComponent.
 *
 * @author      Tuukka Juusela <tuukka.juusela@cs.tamk.fi>
 * @version     2018.1115
 * @since       1.8
 */
public class JSONArray extends JSONComponent{
    /**
     * ArrayList of JSONArrayComponents which hold JSONArray's data.
     */
    ArrayList<JSONArrayComponent> list;

    /**
     * Constructor for the class. Sets key of the JSONArray.
     * @param key
     */
    public JSONArray(String key) {
        setKey(key);
        list = new ArrayList<>();
    }

    /**
     * Returns ArrayList of JSONArrayComponents.
     * @return JSONArrays ArrayList of JSONArrayComponents.
     */
    public ArrayList<JSONArrayComponent> getData() {
        return list;
    }

    /**
     * Returns JSONArray's JSONArrayComponent's LinkedHashMaps. LinkedHashMap's hold data for the JSONArrayComponent.
     * @return ArrayList of LinkedHashMaps from JSONArrayComponents.
     */
    public ArrayList<LinkedHashMap<String, Object>> getLinkedHashMap() {
        ArrayList<LinkedHashMap<String, Object>> mapArray = new ArrayList<>();

        list.forEach(jsonArrayComponent -> mapArray.add(jsonArrayComponent.getLinkedHashMap()));
        return mapArray;
    }

    /**
     * Iterates through given list and adds it as a single JSONArrayComponent to JSONArray's arrayList.
     * @param list to add as a JSONArrayComponent.
     */
    public void add(List<JSONItem> list) {
        this.list.add(createJSONArrayComponent(list));
    }

    /**
     * Adds single JSONItem as a JSONArrayComponent to JSONArray's ArrayList.
     * @param item to as a JSONArrayComponent.
     */
    public void add(JSONItem item) {
        this.list.add(createJSONArrayComponent(item));
    }

    /**
     * Builds JSONArray's data to be written to a file.
     *
     *  Goes through JSONArrays's ArrayList of JSONArrayComponents and converts it to String.
     *  Determines if the data is JSONComponent, String or a some other Object.
     * @return Ready to write String for JSONFileData to use.
     */
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

            builder.append("]");

        } else {
            builder.append(": []");
        }

        return builder.toString();
    }

    /**
     * Creates new JSONArrayComponent from List of JSONItems.
     * @param itemList item list to create JSONArrayComponent from.
     * @return JSONArrayComponent made from List of JSONItems.
     */
    public JSONArrayComponent createJSONArrayComponent(List<JSONItem> itemList) {
        JSONArrayComponent component = new JSONArrayComponent();

        itemList.forEach((JSONItem item) -> component.add(item));

        return component;
    }

    /**
     * Creates new JSONArrayComponent from the JSONItem.
     * @param item to create JSONArrayComponent from.
     * @return JSONArrayComponent made from JSONItem.
     */
    public JSONArrayComponent createJSONArrayComponent(JSONItem item) {
        JSONArrayComponent component = new JSONArrayComponent();
        component.add(item);
        return component;
    }

    /**
     * JSONArrays component to hold data.
     */
    public class JSONArrayComponent extends JSONComponent{
        /**
         * Holds one arrays data and key value.
         */
        private LinkedHashMap<String, Object> map;

        /**
         * Constructor for the inner class.
         */
        public JSONArrayComponent() {
            map = new LinkedHashMap<>();
        }

        /**
         * Parses through JSONItems key value and data and adds it to JSONArrayComponents LinkedHashMap.
         * @param item to add.
         */
        public void add(JSONItem item) {
            map.put(item.getKey(), item.getData());
        }

        /**
         * Removes data from JSONArrayComponent's LinkedHashMap with key's value.
         * @param key to remove from JSONArrayComponent's LinkedHashMap.
         */
        public void remove(String key) {
            map.remove(key);
        }

        /**
         * Returns object from JSONArrayComponent's LinkedHashMap with key's value.
         * @param key to get value from JSONArrayComponent's LinkedHashMap.
         * @return data from LinkedHashMap.
         */
        public Object get(String key) {
            return map.get(key);
        }

        /**
         * Returns JSONArrayComponent's LinkedHashMap which holds all the key values and data for JSONArrayComponent.
         * @return JSONArrayComponent's LinkedHashMap.
         */
        public LinkedHashMap<String, Object> getLinkedHashMap() {
            return map;
        }

        /**
         * Builds JSONArrayComponent's data to be written to a file.
         *
         *  Goes through JSONArrayComponent's data and converts it to String. Determines if the data is
         *  JSONComponent, String or some other Object. Used by JSONArray.
         * @return Ready to write String for JSONArray to use.
         */
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


