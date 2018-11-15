import JSONComponent.JSONFileData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author      Tuukka Juusela <tuukka.juusela@cs.tamk.fi>
 * @version     2018.1115
 * @since       1.8
 *
 * Writer class. Used to write files.
 */
public class JSONWriter {
    public void write(JSONFileData fileData, File file) {
        try (FileWriter fileWriter = new FileWriter(file)){
            BufferedWriter bufferedWriter;
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append(fileData.buildToString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(JSONFileData file) {
        System.out.println(file.buildToString());
    }
}
