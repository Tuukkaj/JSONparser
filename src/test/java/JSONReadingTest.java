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
     * JSONFileData where exampleJSON.json is read using JSONReader.
     */
    private static JSONFileData fd;

    /**
     * Test for getting data from fd.
     */
    @Test
    public void testDataGetting() {
        Assert.assertEquals(((JSONItem)fd.getComponent("firstName")).getData(), "John");
        Assert.assertEquals(((JSONItem)fd.getComponent("lastName")).getData(), "Smith");
        Assert.assertEquals(((JSONItem)fd.getComponent("isAlive")).getData(), true);
        Assert.assertEquals(((JSONItem)fd.getComponent("age")).getData(), 27);
        Assert.assertEquals(((JSONObject)fd.getComponent("address")).getObject("city"), "New York");
        Assert.assertEquals(((JSONObject)fd.getComponent("address")).getObject("state"), "NY");
        Assert.assertEquals(((JSONArray)fd.getComponent("phoneNumbers")).getLinkedHashMap().get(0).get("type"), "home");
        Assert.assertEquals(((JSONArray)fd.getComponent("phoneNumbers")).getLinkedHashMap().get(1).get("number"), "646 555-4567");
        Assert.assertEquals(((JSONArray)fd.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"), "mobile");
        Assert.assertEquals(((JSONArray)fd.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"), "mobile");
        Assert.assertEquals(((JSONItem)fd.getComponent("spouse")).getData(), null);
        Assert.assertTrue(fd.getComponent("children") instanceof JSONArray);
    }

    /**
     * Reads exampleJSON.json to JSONFileData.
     */
    @BeforeClass
    public static void beforeClass() {
        fd = new JSONReader().readFile(new File("src/test/java/exampleJSON.json"));
    }
}
