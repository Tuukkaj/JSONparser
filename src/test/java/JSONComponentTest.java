import JSONComponent.JSONArray;
import JSONComponent.JSONFileData;
import JSONComponent.JSONObject;
import org.junit.*;
import JSONComponent.JSONItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JSONComponentTest {
    private static JSONItem nullItem;
    private static JSONItem stringItem ;
    private static JSONItem intItem;
    private static JSONItem booleanItem;
    private static JSONItem objectItem;
    private static JSONObject testObject;
    private static JSONArray testArray;
    private static JSONFileData testFileData;
    private static JSONObject innerObject;
    private static JSONArray innerArray;

    @BeforeClass
    public static void beforeClass() {
        nullItem = new JSONItem("nullItem", null);
        stringItem = new JSONItem("stringItem", "testString");
        intItem = new JSONItem("intItem", 12);
        booleanItem = new JSONItem("booleanItem", false);
        testObject = new JSONObject("testObject");
        testArray = new JSONArray("testArray");
        innerObject = new JSONObject("testInnerObject");
        innerArray = new JSONArray("testInnerArray");
        objectItem = new JSONItem("objectItem", innerObject);

        testObject.add(nullItem);
        testObject.add(stringItem);
        testObject.add(intItem);
        testObject.add(booleanItem);
        testObject.add(new JSONItem("objectTest", innerObject));
        testObject.add(new JSONItem("arrayTest", innerArray));

        ArrayList<JSONItem> itemList = new ArrayList<>();
        itemList.add(booleanItem);
        itemList.add(stringItem);
        itemList.add(intItem);
        itemList.add(nullItem);
        itemList.add(new JSONItem("objectTest", innerObject));
        itemList.add(new JSONItem("arrayTest", innerArray));
        testArray.add(itemList);

        testFileData = new JSONFileData();
        testFileData.add(nullItem);
        testFileData.add(stringItem);
        testFileData.add(booleanItem);
        testFileData.add(intItem);
        testFileData.add(testObject);
        testFileData.add(testArray);
    }

    @Test
    public void JSONItemTest() {
        Assert.assertEquals(stringItem.getData(),"testString");
        Assert.assertEquals(nullItem.getData(), null);
        Assert.assertEquals(intItem.getData(), 12);
        Assert.assertEquals(objectItem.getData(), innerObject);
        Assert.assertEquals(booleanItem.getData(),false);
    }

    @Test
    public void JSONObjectTest() {
        Assert.assertEquals(testObject.getObject("intItem"),12);
        Assert.assertEquals(testObject.getObject("nullItem"),null);
        Assert.assertEquals(testObject.getObject("stringItem"),"testString");
        Assert.assertEquals(testObject.getObject("booleanItem"),false);
        Assert.assertEquals(testObject.getObject("objectTest"),innerObject);
        Assert.assertEquals(testObject.getObject("arrayTest"),innerArray);
    }

    @Test
    public void JSONArrayTest() {
        LinkedHashMap testLinkedHashMap = testArray.getLinkedHashMap().get(0);
        Assert.assertEquals(testLinkedHashMap.get("intItem"),12);
        Assert.assertEquals(testLinkedHashMap.get("nullItem"),null);
        Assert.assertEquals(testLinkedHashMap.get("stringItem"),"testString");
        Assert.assertEquals(testLinkedHashMap.get("booleanItem"),false);
        Assert.assertEquals(testLinkedHashMap.get("objectTest"),innerObject);
        Assert.assertEquals(testLinkedHashMap.get("arrayTest"),innerArray);
    }


    @Test
    public void JSONFileTest() {
        Assert.assertEquals(((JSONObject) testFileData.getComponent("testObject")).getObject("nullItem"), null);
        Assert.assertEquals(((JSONObject) testFileData.getComponent("testObject")).getObject("stringItem"), "testString");
        Assert.assertEquals(((JSONObject) testFileData.getComponent("testObject")).getObject("intItem"), 12);
        Assert.assertEquals(((JSONObject) testFileData.getComponent("testObject")).getObject("booleanItem"), false);
        Assert.assertEquals(((JSONObject) testFileData.getComponent("testObject")).getObject("objectTest"), innerObject);
        Assert.assertEquals(((JSONObject) testFileData.getComponent("testObject")).getObject("arrayTest"), innerArray);

        Assert.assertEquals(((JSONItem)testFileData.getComponent("nullItem")).getData(), null);
        Assert.assertEquals(((JSONItem)testFileData.getComponent("stringItem")).getData(), "testString");
        Assert.assertEquals(((JSONItem)testFileData.getComponent("intItem")).getData(), 12);
        Assert.assertEquals(((JSONItem)testFileData.getComponent("booleanItem")).getData(), false);

        LinkedHashMap arraysHashMap = ((JSONArray)testFileData.getComponent("testArray")).getLinkedHashMap().get(0);
        Assert.assertEquals(arraysHashMap.get("nullItem"), null);
        Assert.assertEquals(arraysHashMap.get("intItem"), 12);
        Assert.assertEquals(arraysHashMap.get("booleanItem"), false);
        Assert.assertEquals(arraysHashMap.get("stringItem"), "testString");
        Assert.assertEquals(arraysHashMap.get("arrayTest"), innerArray);
        Assert.assertEquals(arraysHashMap.get("objectTest"), innerObject);

    }
}
