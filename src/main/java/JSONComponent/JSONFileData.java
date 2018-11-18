package JSONComponent;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Holds information of the JSON file.
 *
 * @author      Tuukka Juusela
 * @version     2018.1116
 * @since       1.8
 */
public class JSONFileData {
    /**
     * LinkedHashMap of keys and JSONComponents. Holds all of the JSONItem's, JSONObject's and JSONArray of the JSONFile.
     */
    private LinkedHashMap<String, JSONComponent> map;

    /**
     * Constructor of the class. Initializes JSONFileData's LinkedHashMap.
     */
    public JSONFileData() {
        map = new LinkedHashMap<>();
    }

    /**
     * Goes through JSONFileData's LinkedHashMap and creates ArrayList of JSONComponents. Throws
     * InvalidParameterException if key is not found.
     * @return ArrayList<JSONComponent> from JSONFileData's LinkedHashMap.
     */
    public ArrayList<JSONComponent> getComponents() {
        ArrayList<JSONComponent> componentList = new ArrayList<>();
        for(String s: map.keySet()) {
            componentList.add(map.get(s));
        }
        return componentList;
    }

    /**
     * Gets key value from JSONFileData's LinkedHashMap.
     * @param key to get data with.
     * @return JSONComponent from JSONFileData's LinkedHashMap.
     */
    public JSONComponent getComponent(String key) {
        if(!map.keySet().contains(key)) {
            throw new InvalidParameterException("Data with key: " + key + " was not found");
        }

        return map.get(key);
    }

    /**
     * Adds new JSONComponent to JSONFileData's LinkedHashMap.
     * @param component to add.
     */
    public void add(JSONComponent component) {
        map.put(component.getKey(), component);
    }

    /**
     * Removes key's value from JSONFileData's LinkedHashMap. Throws InvalidParameterException if key is not found.
     * @param key to remove value.
     */
    public void remove(String key) {
        if(!map.keySet().contains(key)) {
            throw new InvalidParameterException("Data with key: " + key + " was not found");
        }

        map.remove(key);
    }

    /**
     * Builds JSONFileData's LinkedHashMap to JSON file.
     *
     * Calls LinkedHashMap's JSONComponents and builds the file using their buildToString() -method.
     * @return ready to write/print JSONFile with indentations.
     */
    public String buildToString() {
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

    /**
     * Indents JSONFile's file.
     *
     * Transforms parameter to ArrayList by splitting param with "\n". Checks where "{" or "[" begins
     * and where "}" or "]" ends and indents accordingly.
     * @param textFile file to indent.
     * @return indented file.
     */
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