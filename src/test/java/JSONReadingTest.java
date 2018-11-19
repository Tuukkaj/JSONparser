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
     * Test for getting arrays created from test1.json.
     */
    @Test
    public void firstTestArrays() {
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(0).get("type"), "home");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(1).get("number"), "646 555-4567");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"), "mobile");
        Assert.assertEquals(((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"), "mobile");
        Assert.assertTrue(firstTest.getComponent("children") instanceof JSONArray);
    }

    /**
     * Test for getting items created from test1.json.
     */
    @Test
    public void firstTestItems() {
        Assert.assertEquals(((JSONItem) firstTest.getComponent("firstName")).getData(), "John");
        Assert.assertEquals(((JSONItem) firstTest.getComponent("lastName")).getData(), "Smith");
        Assert.assertEquals(((JSONItem) firstTest.getComponent("isAlive")).getData(), true);
        Assert.assertEquals(((JSONItem) firstTest.getComponent("age")).getData(), 27);
        Assert.assertEquals(((JSONItem) firstTest.getComponent("spouse")).getData(), null);
    }

    /**
     * Test for getting objects created from test1.json.
     */
    @Test
    public void firstTestObjects() {
        Assert.assertEquals(((JSONObject) firstTest.getComponent("address")).getObject("city"), "New York");
        Assert.assertEquals(((JSONObject) firstTest.getComponent("address")).getObject("state"), "NY");
    }

    /**
     * Test for getting arrays created from test2.json.
     */
    @Test
    public void secondTestArrays() {
        Assert.assertEquals(((JSONArray) secondTest.getComponent("PhoneNumbers")).getLinkedHashMap().get(1).get("type"), "home");
        Assert.assertEquals(((JSONArray)((JSONArray) secondTest.getComponent("PhoneNumbers")).getLinkedHashMap().get(3).get("petArray")).getLinkedHashMap().get(0).get("color"), "orange");
        Assert.assertEquals(((JSONObject)((JSONArray)((JSONArray) secondTest.getComponent("PhoneNumbers")).getLinkedHashMap().get(3).get("petArray")).getLinkedHashMap().get(1).get("object3")).getObject("Second"), 2);
    }

    /**
     * Test for getting objects created from test2.json.
     */
    @Test
    public void secondTestObjects() {
        Assert.assertEquals(((JSONObject) secondTest.getComponent("emailAddresses")).getObject("school"), "cool.email@tamk.fi");
        Assert.assertEquals(((JSONObject)((JSONObject) secondTest.getComponent("emailAddresses")).getObject("DifferentObject")).getObject("Third"), 3);
        Assert.assertEquals(((JSONObject)((JSONObject) secondTest.getComponent("emailAddresses")).getObject("DifferentObject")).getObject("First"), 1);

        Assert.assertEquals(((JSONObject)((JSONObject)((JSONObject) secondTest.getComponent("ObjectInObject")).getObject("firstTest")).getObject("DifferentObject")).getObject("false"), false);
        Assert.assertEquals(((JSONObject)((JSONObject)((JSONObject) secondTest.getComponent("ObjectInObject")).getObject("firstTest")).getObject("DifferentObject")).getObject("true"), true);
        Assert.assertEquals(((JSONObject)((JSONObject)((JSONObject) secondTest.getComponent("ObjectInObject")).getObject("firstTest")).getObject("DifferentObject")).getObject("null"), null);

        Assert.assertEquals(((JSONArray)((JSONObject)secondTest.getComponent("ObjectInObject")).getObject("secondTest")).getLinkedHashMap().get(0).get("number"),654123);
        Assert.assertEquals(((JSONObject)((JSONArray)((JSONArray)((JSONObject)secondTest.getComponent("ObjectInObject")).getObject("secondTest")).getLinkedHashMap().get(3).get("petArray")).getLinkedHashMap().get(1).get("object3")).getObject("speed"),14.2f);

        Assert.assertEquals(((JSONObject)((JSONObject)secondTest.getComponent("ObjectInObject")).getObject("TestObject")).getObject("3"),3);
    }

    /**
     * Test for getting items created from test2.json.
     */
    @Test
    public void secondTestItems() {
        Assert.assertEquals(((JSONItem) secondTest.getComponent("name")).getData(), "Tuukka");
        Assert.assertEquals(((JSONItem) secondTest.getComponent("city")).getData(), "tre");
        Assert.assertEquals(((JSONItem) secondTest.getComponent("age")).getData(), 21);
        Assert.assertEquals(((JSONItem) secondTest.getComponent("alive")).getData(), true);
        Assert.assertEquals(((JSONItem) secondTest.getComponent("willToLive")).getData(), null);
    }

    /**
     * Reads test1.json and test2.json to JSONFileData.
     */
    @BeforeClass
    public static void beforeClass() {
        firstTest = new JSONReader().readFile(new File("src/test/java/test1.json"));
        secondTest = new JSONReader().readFile(new File("src/test/java/test2.json"));
    }
}
