import JSONComponent.JSONArray;
import JSONComponent.JSONObject;
import org.junit.*;
import JSONComponent.JSONItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JSONComponentTest {

    @Test
    public void JSONItemTest() {
        JSONItem nullItem = new JSONItem("nullItem", null);
        JSONItem stringItem = new JSONItem("stringItem", "testString");
        JSONItem intItem = new JSONItem("intItem", 12);
        JSONItem objectItem = new JSONItem("objectItem", intItem);

        Assert.assertEquals(stringItem.getData(),"testString");
        Assert.assertEquals(nullItem.getData(), null);
        Assert.assertEquals(intItem.getData(), 12);
        Assert.assertEquals(objectItem.getData(), intItem);
    }

    @Test
    public void JSONObjectTest() {
        JSONObject testObject = new JSONObject("testObject");
        JSONObject innerObject = new JSONObject("testInnerObject");
        JSONArray innerArray = new JSONArray("testInnerArray");

        testObject.add(new JSONItem("intTest", -26));
        testObject.add(new JSONItem("nullTest", null));
        testObject.add(new JSONItem("stringTest", "STRING TEST"));
        testObject.add(new JSONItem("objectTest", innerObject));
        testObject.add(new JSONItem("arrayTest", innerArray));

        Assert.assertEquals(testObject.getObject("intTest"),-26);
        Assert.assertEquals(testObject.getObject("nullTest"),null);
        Assert.assertEquals(testObject.getObject("stringTest"),"STRING TEST");
        Assert.assertEquals(testObject.getObject("objectTest"),innerObject);
        Assert.assertEquals(testObject.getObject("arrayTest"),innerArray);
    }

    @Test
    public void JSONArrayTest() {
        JSONArray testArray = new JSONArray("testArray");
        JSONObject innerObject = new JSONObject("testInnerObject");
        JSONArray innerArray = new JSONArray("testInnerArray");
        ArrayList<JSONItem> itemList = new ArrayList<>();

        itemList.add(new JSONItem("intTest", -5437812));
        itemList.add(new JSONItem("nullTest", null));
        itemList.add(new JSONItem("stringTest", "STRING TEST"));
        itemList.add(new JSONItem("objectTest", innerObject));
        itemList.add(new JSONItem("arrayTest", innerArray));

        testArray.add(itemList);

        LinkedHashMap testLinkedHashMap = testArray.getLinkedHashMap().get(0);
        Assert.assertEquals(testLinkedHashMap.get("intTest"),-5437812);
        Assert.assertEquals(testLinkedHashMap.get("nullTest"),null);
        Assert.assertEquals(testLinkedHashMap.get("stringTest"),"STRING TEST");
        Assert.assertEquals(testLinkedHashMap.get("objectTest"),innerObject);
        Assert.assertEquals(testLinkedHashMap.get("arrayTest"),innerArray);
    }
}
