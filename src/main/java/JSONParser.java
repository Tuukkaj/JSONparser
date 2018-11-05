import reader.JSONReader;
import writer.JSONWriter;

class JSONParser {
    private static JSONReader reader;
    private static JSONWriter writer;

    public static void main(String... args) {
        System.out.println("Author: Tuukka Juusela <tuukka.juusela@cs.tamk.fi>");
        reader = new JSONReader();
        writer = new JSONWriter();
        writer.write("Hello JSONWriter!");
    }
}
