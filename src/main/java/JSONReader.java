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
                    jsonFile.add(linesToJSONArray(list,i));
                    i += determineJSONArraySize(list,i);
                    //System.out.println(list.get(i) + " ?Array?");
                } else if(testLineJSONItem(list.get(i))) {
                    jsonFile.add(lineToJSONItem(list.get(i)));
                    //System.out.println(list.get(i) + " !Item!");
                }
                //if(i < list.size())
                //System.out.println(list.get(i));
            }
        }

        System.out.println(jsonFile.buildToString());
        return jsonFile;
    }

    private int determineJSONArraySize(ArrayList<String> list, int currentLine) {
        for(int i = 0; i < list.size();  i++) {
            if(list.get(i + currentLine).endsWith("],")) {
                return i+1;
            }
        }

        return 0;
    }

    private JSONArray linesToJSONArray(ArrayList<String> list, int currentLine) {
        String arrayName = list.get(currentLine).split(":")[0].replaceFirst("\"", "");
        arrayName = arrayName.substring(0,arrayName.length()-1);

        JSONArray jsonArray = new JSONArray(arrayName);
        ArrayList<ArrayList<JSONItem>> itemArrayList = new ArrayList<>();
        for(int i = currentLine; i < list.size(); i++) {
            if(testLineJSONItem(list.get(i))) {
                ArrayList<JSONItem> itemList = new ArrayList<>();
                for(int j = 0; j < list.size();j++) {
                    if(list.get(i+j).endsWith("}") || list.get(i+j).endsWith("},")) {
                        System.out.println("ENDFOUND");
                        i += j;
                        break;
                    }
                    itemList.add(lineToJSONItem(list.get(i+j)));
                    //System.out.println(list.get(i+j));
                }
                itemArrayList.add(itemList);
            }
        }

        itemArrayList.forEach(a -> jsonArray.addAndCreateJSONArrayComponent("placeholder", a));
        return jsonArray;
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
