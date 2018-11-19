import JSONComponent.*;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import JSONComponent.JSONArray;

/**
 * Main class of the program. Holds methods for reading and writing JSON files. Also holds methods for creation of
 * JSONComponents that are part of the JSONFileData.
 *
 * @author Tuukka Juusela
 * @version 2018.1911
 * @since 1.8
 */
class JSONParser {
    /**
     * JSONReader used to read JSON files.
     */
    private static JSONReader reader;

    /**
     * JSONWriter used to write JSON files.
     */
    private static JSONWriter writer;

    /**
     * Main method. Prints authors name and creates JSONWriter and JSONReader.
     * @param args command line argument. Not in use.
     */
    public static void main(String... args) {
        System.out.println("Author: Tuukka Juusela <tuukka.juusela@cs.tamk.fi>");

        //TEST FILE CREATION
       JSONFileData fd = new JSONFileData();
        fd.add(new JSONItem("name", "Tuukka"));
        fd.add(new JSONItem("city", "tre"));

        fd.add(new JSONItem("age", 21));
        fd.add(new JSONItem("alive", true));
        fd.add(new JSONItem("willToLive", null));

        JSONObject object = new JSONObject("emailAddresses");
        object.add(new JSONItem("school", "cool.email@tamk.fi"));
        object.add(new JSONItem("personal", "wow.last@hotmail.com"));
        object.add(new JSONItem("hobby", "hirvi@kaijakoo.fi"));
        fd.add(object);

        JSONArray array = new JSONArray("PhoneNumbers");
        ArrayList<JSONItem> mobilePhone = new ArrayList<>();
        mobilePhone.add(new JSONItem("type", "mobile"));
        mobilePhone.add(new JSONItem("number", 987654321));
        ArrayList<JSONItem> homePhone = new ArrayList<>();
        homePhone.add(new JSONItem("type", "home"));
        homePhone.add(new JSONItem("number", 12123123));
        ArrayList<JSONItem> workPhone = new ArrayList<>();
        workPhone.add(new JSONItem("type", "work"));
        workPhone.add(new JSONItem("number", 654123));
        array.add(workPhone);
        array.add(homePhone);
        array.add(mobilePhone);
        fd.add(array);

        JSONObject bigObject = new JSONObject("ObjectInObject");
        JSONObject object3 = new JSONObject("OBJECTINOJBECTINOBJECT");
        object3.add(new JSONItem("Third", 3));
        object3.add(new JSONItem("Second", 2));
        object3.add(new JSONItem("first", 1));
        object.add(new JSONItem("DifferentObject", object3));
        bigObject.add(new JSONItem("firstTest", object));
        bigObject.add(new JSONItem("secondTest", array));
        bigObject.add(new JSONItem("objecterino", object3));
        fd.add(bigObject);
        ArrayList<JSONItem> JSONItemList = new ArrayList<>();
        JSONItemList.add(new JSONItem("product", "dog"));
        JSONItemList.add(new JSONItem("color", "orange"));
        JSONItemList.add(new JSONItem("product", "1"));
        JSONArray petArray = new JSONArray("petArray");
        petArray.add(JSONItemList);
        petArray.add(new JSONItem("object3", object3));
        array.add(new JSONItem("petArray", petArray));


        writer = new JSONWriter();
        //writer.write(fd,new File("JSONWritingTests/testJSONFileData.json"));
        //writer.print(fd);
        reader = new JSONReader();
        //writer.print(reader.readFile(new File("src/test/java/exampleJSON.json")));

        //JSONFileData temp = reader.readFile(new File("JSONWritingTests/test3.json"));
        //writer.print(temp);
        //writer.write(temp, new File(("JSONWritingTests/test2.json")));
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
