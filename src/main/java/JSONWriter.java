import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class JSONWriter {
    private BufferedWriter bufferedWriter;

    public JSONWriter(JSONFileData data) {
        try (FileWriter fileWriter = new FileWriter("JSONWritingTests/test.json")){
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(buildToFileText(data));
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String buildToFileText(JSONFileData file) {
        StringBuilder builder = new StringBuilder("{\n");

        builder.append(file.buildToString());

        builder.append("}\n");

        return builder.toString();
    }

    public void print(JSONFileData file) {
        StringBuilder b = new StringBuilder("{\n");

        b.append(file.buildToString());

        b.append("}\n");
        System.out.println(b.toString());
    }
}
