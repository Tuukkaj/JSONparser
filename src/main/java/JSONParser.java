import JSONComponent.JSONArray;
import JSONComponent.JSONFileData;
import JSONComponent.JSONItem;
import JSONComponent.JSONObject;

import java.io.File;
import java.util.ArrayList;

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
        object.add(new JSONItem("personal", "hadgfjas.fadsjdfas@hotmail.com"));
        object.add(new JSONItem("hobby", "asdjasjdh@aksjddadksj.fi"));
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
        array.addAndCreateJSONArrayComponent("workphone", workPhone);
        array.addAndCreateJSONArrayComponent("homephone", homePhone);
        array.addAndCreateJSONArrayComponent("mobilephone", mobilePhone);
        fd.add(array);

        JSONItem testItem = (JSONItem) fd.getComponent("name");
        System.out.println(testItem.getData());

        JSONObject testObject = (JSONObject)fd.getComponent("emailAddresses");
        System.out.println(testObject.getData().get("school"));

        JSONArray testArray = (JSONArray) fd.getComponent("PhoneNumbers");

        writer = new JSONWriter(new File("JSONWritingTests/test.json"));
        writer.print(fd);
        /*
        writer.write(fd);
        writer.changeCurrentFile(new File("JSONWritingTests/test2.json"));
        writer.write(fd);
        writer.changeCurrentFile(new File("JSONWritingTests/test3.json"));
        writer.write(fd);

        reader = new JSONReader();
        ArrayList<String> readerList = reader.readFileToArrayList(new File("JSONWritingTests/wikipediaExample.json"));
        //readerList.forEach(s -> System.out.println(s));
        JSONFileData jsonFile = reader.arrayListToJSONFileData(readerList);
        System.out.println(jsonFile.getComponent("age"));
        System.out.println(jsonFile.buildToString());*/
    }
}
