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
                    //System.out.println(list.get(i) + " ?Array?");
                } else if(testLineJSONItem(list.get(i))) {
                    jsonFile.add(lineToJSONItem(list.get(i)));
                    System.out.println(list.get(i) + " !Item!");
                }
            }
        }

        return jsonFile;
    }

    private JSONItem lineToJSONItem(String line) {
        String replaceComma = line.substring(0,line.length()-1);
        String[] splitLine = replaceComma.split(":");
        /* TODO: Change so that splitLine[1] recognizes data to Object instead of String*/
        return new JSONItem(splitLine[0].replace("\"", ""), splitLine[1]);
    }

    private boolean testLineJSONItem(String line) {
        if(line.endsWith(",")) {
            String replaceComma = line.substring(0,line.length()-1);
            if(!replaceComma.endsWith("}") && !replaceComma.endsWith("]")) {
                return true;
            }
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
