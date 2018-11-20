package jsonParser.JSONComponent;

import java.util.LinkedHashMap;

/**
 * JSONObject can store JSONItems, JSONArrays and other JSONObjects.
 * Used by JSONFileData.<br><br>
 * Example how JSONFileData writes JSONObject to .json file:
 * <pre>
 * {
 *   "emailAddresses": {
 *     "school": "example.email@tamk.fi",
 *     "personal": "example.last@hotmail.com",
 *     "hobby": "example@gmail.fi"
 *   }
 * }
 *</pre>
 * @author      Tuukka Juusela
 * @version     2018.1115
 * @since       1.8
 *
 */
public class JSONObject extends JSONComponent {
    /**
     * Holds all of key pairs and data for the JSONObject.
     */
    private LinkedHashMap<String, Object> table;

    /**
     * Constructor of the class. Sets Objects key.
     * @param key of the JSONObject in JSONFileData.
     */
    public JSONObject(String key) {
        table = new LinkedHashMap<>();
        setKey(key);
    }

    /**
     * Returns key's value from JSONObject's LinkedHashMap.
     * @param key to get value from LinkedHashMap.
     * @return Object from JSONObject's LinkedHashMap.
     */
    public Object getObject(String key) {
        return table.get(key);
    }

    /**
     * Adds new key and value to LinkedHashMap.
     *
     * Puts item's key and data to LinkedHashMap.
     * @param item to add.
     */
    public void add(JSONItem item) {
        table.put(item.getKey(), item.getData());
    }

    /**
     * Removes key's value from JSONObject's LinkedHashMap.
     * @param key to remove value from JSONObject's LinkedHashMap.
     */
    public void remove(String key) {
        table.remove(key);
    }

    /**
     * Returns JSONObjects LinkedHashMap.
     * @return JSONObjects LinkedHashMap.
     */
    public LinkedHashMap<String, Object> getData() {
        return table;
    }

    /**
     * Builds JSONObject's data to be written to a file.
     *
     *  Goes through JSONObject's data and converts it to String. Determines if the data is jsonParser.JSONComponent, String or a
     *  some other Object. Used by JSONFileData.
     * @return Ready to write String for JSONFileData to use.
     */
    @Override
    public String buildToString() {
        StringBuilder b = new StringBuilder(": {\n");
        int i = 0;

        for(String s: table.keySet()) {

            if(table.get(s) instanceof JSONComponent) {
                if (i < table.size() - 1) {
                    b.append("\"" + s + "\" " + ((JSONComponent) table.get(s)).buildToString() + ",\n");
                } else {
                    b.append("\"" + s + "\" " +((JSONComponent) table.get(s)).buildToString() + "\n");
                }

            } else if(table.get(s) instanceof  String) {
                if (i < table.size() - 1) {
                    b.append("\"" + s + "\": \"" + table.get(s) + "\",\n");
                } else {
                    b.append("\"" + s + "\": \"" + table.get(s) + "\"\n");
                }

            } else {
                if (i < table.size() - 1) {
                    b.append("\"" + s + "\": " + table.get(s) + ",\n");
                } else {
                    b.append( "\"" + s + "\": " + table.get(s) + "\n");
                }
            }

            i++;
        }

        b.append("}");

        return b.toString();
    }
}
