import JSONComponent.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import JSONComponent.JSONArray;

class JSONParser {
    private static JSONReader reader;
    private static JSONWriter writer;

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
        //fd.add(bigObject);
        //array.addAndCreateJSONArrayComponent(new JSONItem("Object", object3));
        ArrayList<JSONItem> JSONItemList = new ArrayList<>();
        JSONItemList.add(new JSONItem("product", "dog"));
        JSONItemList.add(new JSONItem("color", "orange"));
        JSONItemList.add(new JSONItem("product", "1"));
        JSONArray petArray = new JSONArray("petArray");
        petArray.add(JSONItemList);
        petArray.add(new JSONItem("object3", object3));
        array.add(new JSONItem("petArray", petArray));


        writer = new JSONWriter();

        reader = new JSONReader();

        JSONFileData temp = reader.readFile(new File("JSONWritingTests/test3.json"));
        //writer.print(temp);
        writer.write(temp, new File(("JSONWritingTests/test2.json")));
    }
}
