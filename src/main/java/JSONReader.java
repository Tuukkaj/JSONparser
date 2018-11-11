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
                    jsonFile.add(linesToJSONObject(list,i,determineJSONObjectSize(list,i)));
                    i += determineJSONObjectSize(list,i);
                } else if(testLineJSONArray(list.get(i))) {
                    jsonFile.add(linesToJSONArray(list,i));
                    i += determineJSONArraySize(list,i);
                } else if(testLineJSONItem(list.get(i))) {
                    jsonFile.add(lineToJSONItem(list.get(i)));
                }
            }
        }

        System.out.println(jsonFile.buildToString());
        return jsonFile;
    }

    private boolean testLineJSONObjectComponent(String line) {
        if(line.contains(":")) {
            return true;
        }

        return false;
    }

    private JSONObject linesToJSONObject(ArrayList<String> list, int currentLine, int endLine) {
        JSONObject object = new JSONObject();
        String objectKey = list.get(currentLine).split(":")[0].trim();
        objectKey = objectKey.substring(0,objectKey.length()-1);
        objectKey = objectKey.substring(1);
        object.setKey(objectKey);

        for(int i = currentLine + 1; i <= currentLine + endLine -1; i++) {
            if(testLineJSONObjectComponent(list.get(i))) {
                String line = list.get(i);
                if(line.endsWith(",")) {
                    line = line.substring(0,line.length()-1);
                }
                String[] splitLine = line.split(":");
                String key = splitLine[0].trim();
                key = key.substring(1);
                key = key.substring(0,key.length()-1);
                Object data = splitLine[1].trim();
                if (data instanceof String && ((String) data).startsWith("\"") && ((String) data).endsWith("\"")) {
                    data = ((String) data).substring(0,((String) data).length()-1);
                    data = ((String) data).substring(1);
                }

                object.add(key,data);
            }
        }

        return object;
    }

    private int determineJSONObjectSize(ArrayList<String> list, int currentLine) {
        for(int i = 0; i < list.size();  i++) {
            if(list.get(i + currentLine).endsWith("},")) {
                return i;
            }
        }

        return 0;
    }

    private int determineJSONArraySize(ArrayList<String> list, int currentLine) {
        for(int i = 0; i < list.size();  i++) {
            if(list.get(i + currentLine).endsWith("],")) {
                return i;
            }
        }

        return 0;
    }

    private JSONArray linesToJSONArray(ArrayList<String> list, int currentLine) {
        String arrayName = list.get(currentLine).split(":")[0].replaceFirst("\"", "");
        arrayName = arrayName.substring(0,arrayName.length()-1).trim();

        JSONArray jsonArray = new JSONArray(arrayName);
        ArrayList<ArrayList<JSONItem>> itemArrayList = new ArrayList<>();
        for(int i = currentLine; i < list.size(); i++) {
            if(testLineJSONItem(list.get(i))) {
                ArrayList<JSONItem> itemList = new ArrayList<>();
                for(int j = 0; j < list.size();j++) {
                    if(list.get(i+j).endsWith("}") || list.get(i+j).endsWith("},")) {
                        i += j;
                        break;
                    }
                    itemList.add(lineToJSONItem(list.get(i+j)));
                }
                itemArrayList.add(itemList);
            }
        }

        itemArrayList.forEach(a -> jsonArray.addAndCreateJSONArrayComponent("placeholder", a));
        return jsonArray;
    }



    private JSONItem lineToJSONItem(String line) {
        String[] splitLine;
        if (line.endsWith((","))) {
            String replaceComma = line.substring(0, line.length() - 1);
            splitLine = replaceComma.split(":");
        } else{
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

        //System.out.println(key+" "+data);

        /* TODO: Change so that splitLine[1] recognizes data to Object instead of String*/
        JSONItem item = new JSONItem(key, data);
        //System.out.println(item.buildToString());
        return item;
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
        } else if(line.trim().endsWith("[],") | line.trim().endsWith("[]")) {
            return true;
        }
        return false;
    }
}
