package JSONComponent;

import java.util.ArrayList;

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

    public void setKey(String key) {
        this.key = key;
    }
}