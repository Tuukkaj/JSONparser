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
        ArrayList<JSONItem> arrayForJSON = new ArrayList<>();
        arrayForJSON.add(new JSONItem("home", 1233123512));
        arrayForJSON.add(new JSONItem("work", 1493481923));
        arrayForJSON.add(new JSONItem("school", 59452342));
        arrayForJSON.add(new JSONItem("gym", 927381273));
        array.addAndCreateJSONArrayComponent("Phone", arrayForJSON);
        fd.add(array);
        writer = new JSONWriter();
        writer.print(fd);
    }
}
