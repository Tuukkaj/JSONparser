import java.io.*;
import java.util.ArrayList;
import JSONComponent.*;

/**
 * @author      Tuukka Juusela <tuukka.juusela@cs.tamk.fi>
 * @version     2018.1116
 * @since       1.8
 *
 * Reads JSON file and creates JSONFileData from it.
 */
public class JSONReader {
    private ArrayList<String> readFileToArrayList(File file) {
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

    public JSONFileData readFile(File file) {
        ArrayList<String> readFile = readFileToArrayList(file);

        return arrayListToJSONFileData(readFile);
    }

    private JSONFileData arrayListToJSONFileData(ArrayList<String> list) {
        JSONFileData jsonFile = null;

        if (list.get(0).equalsIgnoreCase("{") && list.get(list.size()-1).equalsIgnoreCase("}")) {
            jsonFile = new JSONFileData();

            for(int i = 0; i < list.size(); i++) {

                if (testLineJSONObject(list.get(i))) {
                    jsonFile.add(linesToJSONObject(list,i,determineJSONObjectSize(list,i)));
                    i += determineJSONObjectSize(list,i);

                } else if(testLineJSONArray(list.get(i))) {
                    jsonFile.add(linesToJSONArray(list,i, determineJSONArraySize(list,i)));
                    i += determineJSONArraySize(list,i);

                } else if(testLineJSONItem(list.get(i))) {
                    jsonFile.add(lineToJSONItem(list.get(i)));
                }
            }
        }

        return jsonFile;
    }

    private boolean testLineJSONObjectComponent(String line) {
        return line.contains(":");
    }

    private JSONObject linesToJSONObject(ArrayList<String> list, int currentLine, int checkLength) {
        String objectKey = list.get(currentLine).split(":")[0].trim();
        objectKey = objectKey.substring(0,objectKey.length()-1);
        objectKey = objectKey.substring(1);
        JSONObject object = new JSONObject(objectKey);

        for(int i = currentLine+1; i <= currentLine + checkLength; i++) {

            if(testLineJSONArray(list.get(i))) {
                JSONArray tempArray = linesToJSONArray(list, i, determineJSONArraySize(list,i));
                object.add(new JSONItem(tempArray.getKey(), tempArray));
                i += determineJSONArraySize(list,i);

            } else if(testLineJSONObjectComponent(list.get(i))) {
                String line = list.get(i);
                line = line.trim();

                if(line.endsWith(",")) {
                    line = line.substring(0,line.length()-1);
                }

                String[] splitLine = line.split(":");
                String key = splitLine[0].trim();
                key = key.substring(1);
                key = key.substring(0,key.length()-1);
                Object data = splitLine[1].trim();

                if(testIfDataIsObject((String) data)) {
                    data = linesToJSONObject(list,i,determineJSONObjectSize(list,i));
                    i += determineJSONObjectSize(list,i);
                }

                object.add(new JSONItem(key, data));
            }
        }
        return object;
    }

    private boolean testIfDataIsObject(String data) {
        return data.equals("{") | data.endsWith("{");
    }

    private int determineJSONObjectSize(ArrayList<String> list, int currentLine) {
        int objectsFound = 0;

        for(int i = currentLine+1; i < list.size();  i++) {

            if (testIfDataIsObject(list.get(i))) {
                objectsFound++;

            } else if(list.get(i).trim().endsWith("},") || list.get(i).trim().endsWith("}")) {

                if(objectsFound > 0) {
                    objectsFound--;
                } else {
                    return i - currentLine;
                }

            }
        }

        return 0;
    }

    private int determineJSONArraySize(ArrayList<String> list, int currentLine) {
        int arraysFound = 0;

        for(int i = currentLine+1; i < list.size();  i++) {

            if (testLineJSONArray(list.get(i))) {
                arraysFound++;

            } else if(list.get(i).trim().endsWith("],") || list.get(i).trim().endsWith("]")) {

                if(arraysFound > 0) {
                    arraysFound--;
                }else {
                    return i - currentLine;
                }

            }
        }

        return 0;
    }

    private JSONArray linesToJSONArray(ArrayList<String> list, int currentLine, int checkLength) {
        String arrayName = list.get(currentLine).split(":")[0].replaceAll("\"", "");
        arrayName = arrayName.trim();

        JSONArray jsonArray = new JSONArray(arrayName);
        ArrayList<ArrayList<JSONItem>> itemArrayList = new ArrayList<>();

        for(int i = currentLine; i < currentLine + checkLength; i++) {
            ArrayList<JSONItem> itemList = new ArrayList<>();

            if(list.get(i).trim().equals("{")) {
                for(int j = i; j < currentLine+checkLength; j++) {

                    if (testLineJSONObject(list.get(j))) {
                        JSONObject tempObject = linesToJSONObject(list, j, determineJSONObjectSize(list, j));
                        itemList.add(new JSONItem(tempObject.getKey(), tempObject));
                        j += determineJSONObjectSize(list, j);

                    } else if(testLineJSONArray(list.get(j))) {
                        JSONArray tempArray = linesToJSONArray(list, j, determineJSONArraySize(list, j));
                        itemList.add(new JSONItem(tempArray.getKey(), tempArray));
                        j +=  determineJSONArraySize(list,j);

                    } else if (testLineJSONItem(list.get(j))) {
                        itemList.add(lineToJSONItem(list.get(j)));
                    }

                    if (list.get(j).trim().endsWith("}") || list.get(j).trim().endsWith("},")) {
                        i = j;
                        itemArrayList.add(itemList);
                        break;
                    }
                }
            }
        }

        itemArrayList.forEach(jsonArray::add);

        return jsonArray;
    }



    private JSONItem lineToJSONItem(String line) {
        String[] splitLine;

        if (line.endsWith((","))) {
            String replaceComma = line.substring(0, line.length() - 1);
            splitLine = replaceComma.split(":");

        } else {
            splitLine = line.split(":");
        }

        String key = splitLine[0].trim();
        String data = splitLine[1].trim();

        if(data.startsWith("\"") && data.endsWith("\"")) {
            data = data.substring(1);
            data = data.substring(0,data.length()-1);
        }

        if(key.startsWith("\"") && key.endsWith("\"")) {
            key = key.substring(1);
            key = key.substring(0,key.length()-1);
        }

        return new JSONItem(key, data);
    }

    private boolean testLineJSONItem(String line) {
        line = line.trim();

        if(line.endsWith(",")) {
            String replaceComma = line.substring(0,line.length()-1);

            if(!replaceComma.endsWith("}") && !replaceComma.endsWith("]") && line.contains(":") &&
                !line.startsWith("{") && !line.startsWith("[")) {
                return true;
            }

        } else if (line.contains(":") && !line.endsWith("[") && !line.startsWith("{")
                && !line.endsWith("]") && !line.endsWith("}")) {

                return true;
        }

        return false;
    }

    private boolean testLineJSONObject(String line) {
        return line.endsWith("{") && line.contains(":");
    }

    private boolean testLineJSONArray(String line) {
        line = line.trim();

        if(line.endsWith("[") && line.contains(":")) {
            return true;
        } else if(line.trim().endsWith("[],") | line.trim().endsWith("[]")) {
            return true;
        }

        return false;
    }
}
