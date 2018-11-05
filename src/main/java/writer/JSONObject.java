package writer;

import java.util.HashMap;

public class JSONObject {
    private HashMap hMap;

    public JSONObject() {
         hMap = new HashMap();
    }

    public void addKeyPair(String key, String data) {
        hMap.put(key, data);
    }

    public String buildToString() {
        StringBuilder builder = new StringBuilder("{\n");
        builder.append(hMap.get("1") + "\n");

        builder.append("}\n");
        return builder.toString();
    }
}
