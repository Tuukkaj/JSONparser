package writer;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {
    private BufferedWriter bufferedWriter;

    public JSONWriter() {
        try {
            bufferedWriter = createBufferedReader();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private BufferedWriter createBufferedReader() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources/test.json"))){
            return bufferedWriter;
        }
    }

    public void write(String line) {
        String writingLine = "{\n";

        writingLine += line;

        writingLine += "\n}";
        System.out.println(writingLine);
        try {
            bufferedWriter.write(writingLine);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
