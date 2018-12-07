import jsonParser.JSONComponent.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.AfterClass;

import java.util.ArrayList;

/**
 * Tests Writing data.
 *
 * @author      Tuukka Juusela
 * @version     2018.1118
 * @since       1.8
 */
public class JSONWritingTest {
    /**
     * JSONFileData where data is put.
     */
    private static JSONFileData fdAll;

    private static JSONFileData fdObject;

    private static JSONFileData fdArray;

    private static JSONFileData fdItem;

    private static String correctOutPutFDItem;

    /**
     * Expected output of the JSONFileData.
     */
    private static String correctOutPutFDAll;

    /**
     * Tests JSONFileData buildToString().
     */
    @Test
    public void buildToStringAll() {
        Assert.assertEquals(fdAll.buildToString(),correctOutPutFDAll);
    }

    @Test
    public void buildToStringItem() {
        Assert.assertEquals(fdItem.buildToString(), correctOutPutFDItem);
    }

    /**
     * Compiles given data to a JSONFile and creates String for expected output.
     */
    @BeforeClass
    public static void beforeClass() {
        fdAll = new JSONFileData();
        fdAll.add(new JSONItem("name", "Tuukka"));
        fdAll.add(new JSONItem("city", "tre"));

        fdAll.add(new JSONItem("age", 21));
        fdAll.add(new JSONItem("alive", true));
        fdAll.add(new JSONItem("willToLive", null));

        JSONObject object = new JSONObject("emailAddresses");
        object.add(new JSONItem("school", "cool.email@tamk.fi"));
        object.add(new JSONItem("personal", "wow.last@hotmail.com"));
        object.add(new JSONItem("hobby", "hirvi@kaijakoo.fi"));
        fdAll.add(object);

        JSONArray array = new JSONArray("PhoneNumbers");
        ArrayList<JSONItem> mobilePhone = new ArrayList<>();
        mobilePhone.add(new JSONItem("type", "mobile"));
        mobilePhone.add(new JSONItem("number", 987654321));
        ArrayList<JSONItem> homePhone = new ArrayList<>();
        homePhone.add(new JSONItem("type", "home"));
        homePhone.add(new JSONItem("number", 12123123));
        ArrayList<JSONItem> workPhone = new ArrayList<>();
        workPhone.add(new JSONItem("type", "work"));
        workPhone.add(new JSONItem("number", 654123));
        array.add(workPhone);
        array.add(homePhone);
        array.add(mobilePhone);
        fdAll.add(array);

        JSONObject bigObject = new JSONObject("ObjectInObject");
        JSONObject object3 = new JSONObject("OBJECTINOJBECTINOBJECT");
        object3.add(new JSONItem("Third", 3));
        object3.add(new JSONItem("Second", 2));
        object3.add(new JSONItem("first", 1));
        object.add(new JSONItem("DifferentObject", object3));
        bigObject.add(new JSONItem("firstTest", object));
        bigObject.add(new JSONItem("secondTest", array));
        bigObject.add(new JSONItem("objecterino", object3));
        fdAll.add(bigObject);
        ArrayList<JSONItem> JSONItemList = new ArrayList<>();
        JSONItemList.add(new JSONItem("product", "dog"));
        JSONItemList.add(new JSONItem("color", "orange"));
        JSONItemList.add(new JSONItem("product", "1"));
        JSONArray petArray = new JSONArray("petArray");
        petArray.add(JSONItemList);
        petArray.add(new JSONItem("object3", object3));
        array.add(new JSONItem("petArray", petArray));

        fdItem = new JSONFileData();
        fdItem.add(new JSONItem("intTest", 12));
        fdItem.add(new JSONItem("StringTest", "test"));
        fdItem.add(new JSONItem("nullTest", null));

        correctOutPutFDItem ="{\n" +
                "  \"intTest\": 12,\n" +
                "  \"StringTest\": \"test\",\n" +
                "  \"nullTest\": null\n" +
                "}\n";

        correctOutPutFDAll = "{\n" +
                "  \"name\": \"Tuukka\",\n" +
                "  \"city\": \"tre\",\n" +
                "  \"age\": 21,\n" +
                "  \"alive\": true,\n" +
                "  \"willToLive\": null,\n" +
                "  \"emailAddresses\": {\n" +
                "    \"school\": \"cool.email@tamk.fi\",\n" +
                "    \"personal\": \"wow.last@hotmail.com\",\n" +
                "    \"hobby\": \"hirvi@kaijakoo.fi\",\n" +
                "    \"DifferentObject\" : {\n" +
                "      \"Third\": 3,\n" +
                "      \"Second\": 2,\n" +
                "      \"first\": 1\n" +
                "    }\n" +
                "  },\n" +
                "  \"PhoneNumbers\": [\n" +
                "    {\n" +
                "      \"type\": \"work\",\n" +
                "      \"number\": 654123\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"home\",\n" +
                "      \"number\": 12123123\n" +
                "    },\n" +
                "    {\n" +
                "      \"type\": \"mobile\",\n" +
                "      \"number\": 987654321\n" +
                "    },\n" +
                "    {\n" +
                "      \"petArray\" : [\n" +
                "        {\n" +
                "          \"product\": 1,\n" +
                "          \"color\": \"orange\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"object3\" : {\n" +
                "            \"Third\": 3,\n" +
                "            \"Second\": 2,\n" +
                "            \"first\": 1\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"ObjectInObject\": {\n" +
                "    \"firstTest\" : {\n" +
                "      \"school\": \"cool.email@tamk.fi\",\n" +
                "      \"personal\": \"wow.last@hotmail.com\",\n" +
                "      \"hobby\": \"hirvi@kaijakoo.fi\",\n" +
                "      \"DifferentObject\" : {\n" +
                "        \"Third\": 3,\n" +
                "        \"Second\": 2,\n" +
                "        \"first\": 1\n" +
                "      }\n" +
                "    },\n" +
                "    \"secondTest\" : [\n" +
                "      {\n" +
                "        \"type\": \"work\",\n" +
                "        \"number\": 654123\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"home\",\n" +
                "        \"number\": 12123123\n" +
                "      },\n" +
                "      {\n" +
                "        \"type\": \"mobile\",\n" +
                "        \"number\": 987654321\n" +
                "      },\n" +
                "      {\n" +
                "        \"petArray\" : [\n" +
                "          {\n" +
                "            \"product\": 1,\n" +
                "            \"color\": \"orange\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"object3\" : {\n" +
                "              \"Third\": 3,\n" +
                "              \"Second\": 2,\n" +
                "              \"first\": 1\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"objecterino\" : {\n" +
                "      \"Third\": 3,\n" +
                "      \"Second\": 2,\n" +
                "      \"first\": 1\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    /**
     * Sets String correctOutPutFDAll and JSONFileData fdAll to null.
     */
    @AfterClass
    public static void afterClass() {
        correctOutPutFDAll = null;
        fdAll = null;
    }
}
