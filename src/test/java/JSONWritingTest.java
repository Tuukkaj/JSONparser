import JSONComponent.*;
import org.junit.*;

public class JSONWritingTest {
    private JSONWriter writer;


    @BeforeClass
    public void beforeClass() {
        writer = new JSONWriter();
    }

    @AfterClass
    public void afterClass() {
        writer = null;
    }
}
