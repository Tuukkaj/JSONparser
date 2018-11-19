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
        Assert.assertEquals("home",((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(0).get("type"));
        Assert.assertEquals("646 555-4567",((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(1).get("number"));
        Assert.assertEquals("mobile", ((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"));
        Assert.assertEquals("mobile", ((JSONArray) firstTest.getComponent("phoneNumbers")).getLinkedHashMap().get(2).get("type"));
        Assert.assertTrue(firstTest.getComponent("children") instanceof JSONArray);
    }

    /**
     * Test for getting items created from test1.json.
     */
    @Test
    public void firstTestItems() {
        Assert.assertEquals("John",((JSONItem) firstTest.getComponent("firstName")).getData());
        Assert.assertEquals("Smith",((JSONItem) firstTest.getComponent("lastName")).getData());
        Assert.assertEquals(true,((JSONItem) firstTest.getComponent("isAlive")).getData());
        Assert.assertEquals( 27,((JSONItem) firstTest.getComponent("age")).getData());
        Assert.assertEquals(null,((JSONItem) firstTest.getComponent("spouse")).getData());
    }

    /**
     * Test for getting objects created from test1.json.
     */
    @Test
    public void firstTestObjects() {
        Assert.assertEquals( "New York",((JSONObject) firstTest.getComponent("address")).getObject("city"));
        Assert.assertEquals("NY",((JSONObject) firstTest.getComponent("address")).getObject("state"));
    }

    /**
     * Test for getting arrays created from test2.json.
     */
    @Test
    public void secondTestArrays() {
        Assert.assertEquals( "home",((JSONArray) secondTest.getComponent("PhoneNumbers")).getLinkedHashMap().get(1).get("type"));
        Assert.assertEquals("orange",((JSONArray)((JSONArray) secondTest.getComponent("PhoneNumbers")).getLinkedHashMap().get(3).get("petArray")).getLinkedHashMap().get(0).get("color"));
        Assert.assertEquals(2,((JSONObject)((JSONArray)((JSONArray) secondTest.getComponent("PhoneNumbers")).getLinkedHashMap().get(3).get("petArray")).getLinkedHashMap().get(1).get("object3")).getObject("Second"));
    }

    /**
     * Test for getting objects created from test2.json.
     */
    @Test
    public void secondTestObjects() {
        Assert.assertEquals("cool.email@tamk.fi",((JSONObject) secondTest.getComponent("emailAddresses")).getObject("school"));
        Assert.assertEquals(3,((JSONObject)((JSONObject) secondTest.getComponent("emailAddresses")).getObject("DifferentObject")).getObject("Third") );
        Assert.assertEquals(1,((JSONObject)((JSONObject) secondTest.getComponent("emailAddresses")).getObject("DifferentObject")).getObject("First"));

        Assert.assertEquals(false,((JSONObject)((JSONObject)((JSONObject) secondTest.getComponent("ObjectInObject")).getObject("firstTest")).getObject("DifferentObject")).getObject("false") );
        Assert.assertEquals(true,((JSONObject)((JSONObject)((JSONObject) secondTest.getComponent("ObjectInObject")).getObject("firstTest")).getObject("DifferentObject")).getObject("true") );
        Assert.assertEquals(null,((JSONObject)((JSONObject)((JSONObject) secondTest.getComponent("ObjectInObject")).getObject("firstTest")).getObject("DifferentObject")).getObject("null"));

        Assert.assertEquals(654123,((JSONArray)((JSONObject)secondTest.getComponent("ObjectInObject")).getObject("secondTest")).getLinkedHashMap().get(0).get("number"));
        Assert.assertEquals(14.2f,((JSONObject)((JSONArray)((JSONArray)((JSONObject)secondTest.getComponent("ObjectInObject")).getObject("secondTest")).getLinkedHashMap().get(3).get("petArray")).getLinkedHashMap().get(1).get("object3")).getObject("speed"));

        Assert.assertEquals(3,((JSONObject)((JSONObject)secondTest.getComponent("ObjectInObject")).getObject("TestObject")).getObject("3"));
    }

    /**
     * Test for getting items created from test2.json.
     */
    @Test
    public void secondTestItems() {
        Assert.assertEquals("Tuukka",((JSONItem) secondTest.getComponent("name")).getData());
        Assert.assertEquals("tre",((JSONItem) secondTest.getComponent("city")).getData());
        Assert.assertEquals(21,((JSONItem) secondTest.getComponent("age")).getData());
        Assert.assertEquals(true,((JSONItem) secondTest.getComponent("alive")).getData());
        Assert.assertEquals(null,((JSONItem) secondTest.getComponent("willToLive")).getData());
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
