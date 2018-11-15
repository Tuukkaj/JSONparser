package JSONComponent;

/**
 * @author      Tuukka Juusela <tuukka.juusela@cs.tamk.fi>
 * @version     2018.1115
 * @since       1.8
 *
 * Base class for all of the JSONFiles.
 */
public abstract class JSONComponent {
    /**
     * Key of the object in JSONFile.
     */
    private String key;

    /**
     * Builds JSONObjects data to String format.
     * @return
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