package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    private BufferedReader bufferedReader;

    public JSONReader() {
        try {
            bufferedReader = createBufferedReader();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private BufferedReader createBufferedReader() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/test.json"))){
            return bufferedReader;
        }
    }
}
