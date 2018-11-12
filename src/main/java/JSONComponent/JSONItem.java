package JSONComponent;

public class JSONItem extends JSONComponent {
    private Object data;

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
            } else if (data.equals(null)) {
                data = null;
            } else if (((String) data).matches("\\d+")) {
                data = Integer.parseInt((String) data);
            } else if (((String) data).matches("[-+]?[0-9]*\\.?[0-9]+")) {
                data = Float.parseFloat((String) data);
            }
        }

        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void delete() {
        setKey(null);
        data = null;
    }

    @Override
    public String buildToString() {
        if (data instanceof String) {
            return ": \"" + data+"\"";
        } else {
            return ": " + data;
        }
    }
}