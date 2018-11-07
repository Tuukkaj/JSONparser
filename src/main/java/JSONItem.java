class JSONItem extends JSONComponent {
    private Object data;

    public JSONItem(String key, Object data) {
        setKey(key);
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