class JSONItem extends JSONComponent {
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
        if (data instanceof String) {
            return ": \"" + data+"\"";
        } else {
            return ": " + data;
        }
    }
}