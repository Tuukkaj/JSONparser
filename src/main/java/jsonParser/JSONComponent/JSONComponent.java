package jsonParser.JSONComponent;

/**
 * Base class for all of the JSONFiles.
 *
 * @author      Tuukka Juusela
 * @version     2018.1115
 * @since       1.8
 */
public abstract class JSONComponent {
    /**
     * Key of the object in JSONFile.
     */
    private String key;

    /**
     * Builds JSONObjects data to String format.
     * @return ready to print String of the jsonParser.JSONComponent.
     */
    abstract String buildToString();

    /**
     * Retuns key of the JSON Object.
     * @return key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key value.
     * @param key value to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
}