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
    private static JSONFileData fd;

    /**
     * Expected output of the JSONFileData.
     */
    private static String correctOutPut;

    /**
     * Tests JSONFileData buildToString().
     */
    @Test
    public void buildToStringTest() {
        Assert.assertTrue(fd.buildToString().equals(correctOutPut));
    }

    /**
     * Compiles given data to a JSONFile and creates String for expected output.
     */
    @BeforeClass
    public static void beforeClass() {
        fd = new JSONFileData();
        fd.add(new JSONItem("name", "Tuukka"));
        fd.add(new JSONItem("city", "tre"));

        fd.add(new JSONItem("age", 21));
        fd.add(new JSONItem("alive", true));
        fd.add(new JSONItem("willToLive", null));

        JSONObject object = new JSONObject("emailAddresses");
        object.add(new JSONItem("school", "cool.email@tamk.fi"));
        object.add(new JSONItem("personal", "wow.last@hotmail.com"));
        object.add(new JSONItem("hobby", "hirvi@kaijakoo.fi"));
        fd.add(object);

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
        fd.add(array);

        JSONObject bigObject = new JSONObject("ObjectInObject");
        JSONObject object3 = new JSONObject("OBJECTINOJBECTINOBJECT");
        object3.add(new JSONItem("Third", 3));
        object3.add(new JSONItem("Second", 2));
        object3.add(new JSONItem("first", 1));
        object.add(new JSONItem("DifferentObject", object3));
        bigObject.add(new JSONItem("firstTest", object));
        bigObject.add(new JSONItem("secondTest", array));
        bigObject.add(new JSONItem("objecterino", object3));
        fd.add(bigObject);
        ArrayList<JSONItem> JSONItemList = new ArrayList<>();
        JSONItemList.add(new JSONItem("product", "dog"));
        JSONItemList.add(new JSONItem("color", "orange"));
        JSONItemList.add(new JSONItem("product", "1"));
        JSONArray petArray = new JSONArray("petArray");
        petArray.add(JSONItemList);
        petArray.add(new JSONItem("object3", object3));
        array.add(new JSONItem("petArray", petArray));

        correctOutPut = "{\n" +
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
     * Sets String correctOutPut and JSONFileData fd to null.
     */
    @AfterClass
    public static void afterClass() {
        correctOutPut = null;
        fd = null;
    }
}
