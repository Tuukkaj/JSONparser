package jsonParser;

import jsonParser.JSONComponent.*;

import java.io.File;

import jsonParser.JSONComponent.JSONArray;

/**
 * Main class of the program. Holds methods for reading and writing JSON files. Also holds methods for creation of
 * JSONComponents that are part of the JSONFileData.
 *<br><br>
 * Using jsonParser.JSONParser for writing: First create JSONFileData and add any jsonParser.JSONComponent(item, object, array) you want.
 * Give JSONFileData and file to write on to write -method.
 *<br><br>
 * Using jsonParser.JSONParser for reading: Give file to read to read -method. It returns JSONFileData created from the file.
 * You can access data of the JSONFileData with JSONFileData's methods getComponent and getComponents.
 *
 * @author Tuukka Juusela
 * @version 2018.1911
 * @since 1.8
 */
public class JSONParser {
    /**
     * jsonParser.JSONReader used to read JSON files.
     */
    private static JSONReader reader;

    /**
     * jsonParser.JSONWriter used to write JSON files.
     */
    private static JSONWriter writer;

    /**
     * Programs main class. Prints authors name and creates jsonParser.JSONWriter and jsonParser.JSONReader.
     */
    public JSONParser() {
        System.out.println("Author: Tuukka Juusela <tuukka.juusela@cs.tamk.fi>");
        writer = new JSONWriter();
        reader = new JSONReader();
    }

    /**
     * Creates new JSONItem from parameters.
     *
     * @param key of JSONItem.
     * @param data of JSONItem. String, Boolean, Int, Float and null.
     * @return JSONItem created from parameters.
     */
    public JSONItem createJSONItem(String key, Object data) {
        return new JSONItem(key,data);
    }

    /**
     * Creates new JSONObject with key from parameter.
     *
     * @param key of JSONObject.
     * @return JSONObject created with key from parameter.
     */
    public JSONObject createJSONObject(String key) {
        return new JSONObject(key);
    }

    /**
     * Creates new JSONArray with key from parameter.
     * @param key of JSONArray.
     * @return JSONArray created with key from parameter.
     */
    public JSONArray createJSONArray(String key) {
        return new JSONArray(key);
    }

    /**
     * Creates JSONFileData where JSONComponents are inserted.
     * @return new JSONFileData.
     */
    public JSONFileData createJSONFileData() {
        return new JSONFileData();
    }

    /**
     * Writes JSONFileData's data to given file.
     *
     * @param fileData JSONFileData to write.
     * @param fileToWriteOn file to write on.
     */
    public void write(JSONFileData fileData, File fileToWriteOn) {
        writer.write(fileData, fileToWriteOn);
    }

    /**
     * Prints JSONFileData to console. Simulates writing a file.
     * @param fileData JSONFileData to print.
     */
    public void print(JSONFileData fileData) {
        writer.print(fileData);
    }

    /**
     * Reads File location and converts it to JSONFileData.
     * @param file to read.
     * @return JSONFileData created from file.
     */
    public JSONFileData read(File file) {
        return reader.readFile(file);
    }
}
