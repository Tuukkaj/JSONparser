import org.junit.*;
import JSONComponent.JSONItem;

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
}
