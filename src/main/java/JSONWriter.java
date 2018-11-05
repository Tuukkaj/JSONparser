import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class JSONWriter {
    private BufferedWriter bufferedWriter;

    public JSONWriter() {
        try {
            bufferedWriter = createBufferedWriter(); 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private BufferedWriter createBufferedWriter() throws IOException {
        try (FileWriter fw = new FileWriter("src/main/resources/test.txt"); BufferedWriter bw = new BufferedWriter(fw)){
            return bw;
        }
    }
}
