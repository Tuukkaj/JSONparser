import java.io.*;
import java.util.ArrayList;
import JSONComponent.*;

public class JSONReader {
    public ArrayList<String> readFileToArrayList(File file) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader;
        try (FileReader fileReader = new FileReader(file)){
            bufferedReader =  new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public JSONFileData arrayListToJSONFileData(ArrayList<String> list) {
        JSONFileData jsonFile = null;

        if (list.get(0).equalsIgnoreCase("{") && list.get(list.size()-1).equalsIgnoreCase("}")) {
            jsonFile = new JSONFileData();

            for(int i = 0; i < list.size(); i++) {

                if (testLineJSONObject(list.get(i))) {
                   // System.out.println(list.get(i) + " &Object&");
                } else if(testLineJSONArray(list.get(i))) {
                    System.out.println(list.get(i) + " ?Array?");
                } else if(testLineJSONItem(list.get(i))) {
                    //System.out.println(list.get(i) + " !Item!");
                }
            }
        }

        return jsonFile;
    }


    private boolean testLineJSONItem(String line) {
        if(line.endsWith(",")) {
            return true;
        }

        return false;
    }

    private boolean testLineJSONObject(String line) {
        if(line.endsWith("{") && line.contains(":")) {
            return true;
        }

        return false;
    }

    private boolean testLineJSONArray(String line) {
        if(line.endsWith("[") && line.contains(":")) {
            return true;
        }

        return false;
    }
}
