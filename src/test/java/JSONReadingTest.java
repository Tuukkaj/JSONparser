import JSONComponent.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;


/**
 * Tests JSONReader for correctly reading data.
 *
 * @author      Tuukka Juusela
 * @version     2018.1118
 * @since       1.8
 */
public class JSONReadingTest {
    /**
     * JSONFileData where test1.json is read using JSONReader.
     */
    private static JSONFileData firstTest;

    /**
     * JSONFileData where XXX.json is read using JSONReader.
     */
    private static JSONFileData secondTest;

    /**
     * Test for getting data from firstTest.
     */
    @Test
    public void firstTest() {
        Assert.assertEquals(((JSONItem) firstTest.getComponent("firstName")).getData(), "John");
        Assert.assertEquals(((JSONItem) firstTest.getComponent("lastName")).getData(), "Smith");
        Assert.assertEquals(((JSONItem) firstTest.getComponent("isAlive")).getData(), true);
        Assert.assertEquals(((JSONItem) firstTest.getComponent("age")).getData(), 27);
        Assert.assertEquals(((JSONObject) firstTest.getComponent("address")).getObject("city"), "New York");
        Assert.assertEquals(((JSONObject) firstTest.getComponent("address")).getObject("state"), "NY");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(0).get("type"), "home");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(1).get("number"), "646 555-4567");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"), "mobile");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"), "mobile");
        Assert.assertEquals(((JSONItem) firstTest.getComponent("spouse")).getData(), null);
        Assert.assertTrue(firstTest.getComponent("children") instanceof JSONArray);
    }

    /**
     * Reads test1.json to JSONFileData.
     */
    @BeforeClass
    public static void beforeClass() {
        firstTest = new JSONReader().readFile(new File("src/test/java/test1.json"));
        secondTest = new JSONReader().readFile(new File("src/test/java/test2.json"));
    }
}
