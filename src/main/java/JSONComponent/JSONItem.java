package JSONComponent;

/**
 * Base item of JSONFiles. Used by JSONFileData to hold data and by JSONObject and JSONArray to transfer data.
 *
 * Example how JSONFileData writes JSONItems to .json file:
 *
 * {
 *  "firstName": "John",
 *  "lastName": "Smith"
 * }
 *
 * @author      Tuukka Juusela
 * @version     2018.1115
 * @since       1.8
 */
public class JSONItem extends JSONComponent {
    /**
     * Data of the item.
     */
    private Object data;

    /**
     * Constructor for the class. Sets key and data of the item .
     *
     * Sets key of JSONItem which is used in JSONFileData. Checks data's type if data is instance of String and
     * sets it to a correct value. Throws IllegalArgumentException if data is not String, integer, float, double,
     * boolean, null, JSONObject or JSONArray.
     *
     * @param key Key of the JSONItem.
     * @param data Data of the JSONItem.
     */
    public JSONItem(String key, Object data) {
        setKey(key);
        if(data instanceof String) {
            if (data.equals("true")) {
                data = true;
            } else if (data.equals("false")) {
                data = false;
            } else if (((String) data).startsWith("\"") && ((String) data).endsWith("\"")) {
                data = ((String) data).substring(0, ((String) data).length() - 1);
                data = ((String) data).substring(1);
            } else if (data.equals(null) || data.equals("null")) {
                data = null;
            } else if (((String) data).matches("\\d+")) {
                data = Integer.parseInt((String) data);
            } else if (((String) data).matches("[-+]?[0-9]*\\.?[0-9]+")) {
                data = Float.parseFloat((String) data);
            }
        } else if(!checkForCorrectValue(data)) {
            throw new IllegalArgumentException("JSON Only accepts String, integer, float, boolean, null, JSONObjects or" +
                    " JSONArrays.");
        }

        this.data = data;
    }

    /**
     * Tests if data is acceptable.
     *
     * Tests for Float, Double, Boolean, Integer, JSONObject, JSONArray and null.
     *
     * @param data to test.
     * @return true if data is acceptable. False if not.
     */
    private boolean checkForCorrectValue(Object data) {
        if(data instanceof Float) {
            return true;

        } else if(data instanceof Double) {
            return true;

        } else if(data instanceof Boolean) {
            return true;

        } else if(data instanceof Integer) {
            return true;

        } else if(data instanceof JSONObject) {
            return true;

        } else if(data instanceof JSONArray) {
            return true;

        } else if(data == null) {
            return true;
        }

        return false;
    }

    /**
     * Returns data of the JSONItem.
     * @return JSONItems data.
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets key and data values to null.
     */
    public void delete() {
        setKey(null);
        data = null;
    }

    /**
     * Builds JSONItem's data to String to write into a file.
     * @return Ready to write String for JSONFileData to use.
     */
    @Override
    public String buildToString() {
        if (data instanceof String) {
            return ": \"" + data+"\"";
        } else {
            return ": " + data;
        }
    }
}