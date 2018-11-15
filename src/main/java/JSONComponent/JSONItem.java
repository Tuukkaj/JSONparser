package JSONComponent;

/**
 * @author      Tuukka Juusela <tuukka.juusela@cs.tamk.fi>
 * @version     2018.1115
 * @since       1.8
 *
 * Base item of JSONFiles. Used by JSONFileData to hold data and by JSONObject and JSONArray to add and hold data.
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
     * sets it to a correct value.
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
        }

        this.data = data;
    }

    /**
     * Returns data of the JSONItem.
     * @return
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