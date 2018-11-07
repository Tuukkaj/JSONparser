abstract class JSONComponent {
    /**
     * Key of the object in JSONFile.
     */
    String key;

    /**
     * Builds JSONObjects data to String format.
     * @return
     */
    abstract String buildToString();

    /**
     * Retuns key of the JSON Object.
     * @return key.
     */
    abstract String getKey();
}