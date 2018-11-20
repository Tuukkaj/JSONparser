package jsonParser;

import jsonParser.JSONComponent.JSONFileData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writer class. Used to write files.
 *
 * @author      Tuukka Juusela
 * @version     2018.1115
 * @since       1.8
 *
 */
public class JSONWriter {
    /**
     * Writes JSONFileData to a file.
     *
     * Calls JSONFileData's buildToString() method to generate String to write.
     * @param fileData JSONFile data to write.
     * @param file File to write.
     */
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

    /**
     * Prints JSONFileData. Simulates writing to a file.
     * @param file JSONFileData data to print.
     */
    public void print(JSONFileData file) {
        System.out.println(file.buildToString());
    }
}
