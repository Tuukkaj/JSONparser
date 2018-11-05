class JSONParser {
    private static JSONReader reader;
    private static JSONWriter writer;

    public static void main(String... args) {
        System.out.println("Author: Tuukka Juusela <tuukka.juusela@cs.tamk.fi>");

        JSONFileData fd = new JSONFileData();
        fd.addItem(new JSONItem("name", "Tuukka"));
        fd.addItem(new JSONItem("city", "tre"));
        fd.addItem(new JSONItem("age", 21));
        fd.addItem(new JSONItem("alive", true));

        JSONArray array = new JSONArray("emailAddresses");
        array.add(new JSONItem("school", "gkjdfa.gajgfk@tamk.fi"));
        array.add(new JSONItem("personal", "hadgfjas.fadsjdfas@hotmail.com"));
        fd.addArray(array);

        writer = new JSONWriter();
        writer.print(fd);
    }
}
