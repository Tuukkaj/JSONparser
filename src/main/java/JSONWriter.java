import JSONComponent.JSONFileData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class JSONWriter {
    private BufferedWriter bufferedWriter;
    private File currentFile;

    public JSONWriter(File file) {
        this.currentFile = file;
    }

    public void changeCurrentFile(File file) {
        currentFile = file;
    }

    public void write(JSONFileData fileData) {
        try (FileWriter fileWriter = new FileWriter(currentFile)){
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
