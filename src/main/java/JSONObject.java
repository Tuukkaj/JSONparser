abstract class JSONObject {
    String key;
    abstract String buildToString();
    abstract String getKey();
    abstract Object getData();
}