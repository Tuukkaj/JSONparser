import java.io.*;
import java.util.ArrayList;
import JSONComponent.*;

/**
 * Reads JSON file and creates JSONFileData from it.
 *
 * @author      Tuukka Juusela
 * @version     2018.1116
 * @since       1.8
 */
class JSONReader {
    /**
     * Reads file's lines to ArrayList of Strings.
     * @param file to read.
     * @return File's lines as ArrayList.
     */
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

    /**
     * Reads file and turns it to JSONFileData.
     * @param file to read.
     * @return JSONFileData read from parameter file.
     */
    public JSONFileData readFile(File file) {
        ArrayList<String> readFile = readFileToArrayList(file);

        return arrayListToJSONFileData(readFile);
    }

    /**
     * Turns ArrayList of Strings from file to JSONFileData.
     *
     * Checks if ArrayList begins and ends with correctly. Then starts going through each line and determining which
     * line is JSONObject, JSONArray or JSONItem. If detects JSONObject or JSONArray skips forward to include their
     * contents correctly.
     * @param list to make JSONFileData from.
     * @return JSONFileData created from ArrayList.
     */
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

    /**
     * Tests if line is some kind of JSONComponent.
     * @param line to determine from.
     * @return true if line is JSONComponent. False if not.
     */
    private boolean testLineJSONObjectComponent(String line) {
        return line.contains(":");
    }

    /**
     * Turns selected section of ArrayList JSONObject.
     *
     * Goes through the lines and checks if content is JSONArray or JSONObject. If line is either one of those, it
     * adds it to the JSONObject. Skips the lines of just added JSONArray or JSONObject and proceeds reading the
     * list.
     *
     * @param list ArrayList of file's lines.
     * @param currentLine line to start going trough the list.
     * @param checkLength line to end going through the list.
     * @return JSONObject created from list.
     */
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

    /**
     * Test if parameter is a JSONObject.
     * @param data line to test.
     * @return True if is JSONObject. False if not.
     */
    private boolean testIfDataIsObject(String data) {
        return data.equals("{") | data.endsWith("{");
    }

    /**
     * Determines the size of JSONObject.
     *
     * Goes through the list and finds the ending curly bracket. Counts how many lines it took to find
     * it and returns it.
     * @param list ArrayList of file's lines.
     * @param currentLine line to start going through the list.
     * @return length of the JSONObject.
     */
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

    /**
     * Determines the size of JSONArray.
     *
     * Goes through the list and finds the ending square bracket. Counts how many lines it took to
     * find it and returns it.
     * @param list ArrayList of file's lines.
     * @param currentLine line to start going through the list.
     * @return length of the JSONArray.
     */
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

    /**
     * Turns selected section of ArrayList JSONArray.
     *
     * Goes through the lines and checks if content is JSONArray or JSONObject. If line is either one of those, it
     * adds it to the JSONArray. Skips the lines of just added JSONArray or JSONObject and proceeds reading the
     * list.
     *
     * @param list ArrayList of file's lines.
     * @param currentLine line to start going trough the list.
     * @param checkLength line to end going through the list.
     * @return JSONArray created from list.
     */
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

    /**
     * Creates JSONItem from line. Checks and trims the line from " " and white spaces.
     * @param line to create JSONItem from.
     * @return JSONItem created from line.
     */
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

    /**
     * Tests if JSONItem can be created from parameter line.
     * @param line to check.
     * @return True if line can be transformed to JSONItem. False if not.
     */
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

    /**
     * Tests if line is a beginning of JSONObject.
     * @param line to check.
     * @return True if line is beginning JSONObject. False if not.
     */
    private boolean testLineJSONObject(String line) {
        return line.endsWith("{") && line.contains(":");
    }

    /**
     * Tests if line is a beginning of JSONArray.
     * @param line to check.
     * @return True if line is beginning JSONArray. False if not.
     */
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
