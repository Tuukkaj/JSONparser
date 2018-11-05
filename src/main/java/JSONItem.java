class JSONItem extends JSONObject {
    private Object data;

    public JSONItem(String key, Object data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public String getKey() {
        return key;
    }

    public Object getData() {
        return data;
    }

    public void delete() {
        key = null;
        data = null;
    }

    @Override
    public String buildToString() {
        return key +  ": " + data;
    }
}