import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

class JSONParser {
    private static JSONReader reader;
    private static JSONWriter writer;

    public static void main(String... args) {
        System.out.println("Author: Tuukka Juusela <tuukka.juusela@cs.tamk.fi>");

        JSONFileData fd = new JSONFileData();
        fd.add(new JSONItem("name", "Tuukka"));
        fd.add(new JSONItem("city", "tre"));
        fd.add(new JSONItem("age", 21));
        fd.add(new JSONItem("alive", true));
        fd.add(new JSONItem("willToLive", null));

        JSONObject object = new JSONObject("emailAddresses");
        object.add(new JSONItem("school", "gkjdfa.gajgfk@tamk.fi"));
        object.add(new JSONItem("personal", "hadgfjas.fadsjdfas@hotmail.com"));
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
        writer = new JSONWriter(new File("JSONWritingTest/test.json"),fd);
        //writer.write(fd);
        writer.print(fd);
    }
}
