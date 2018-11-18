import JSONComponent.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class JSONReadingTest {
    public static JSONReader reader;
    public static JSONFileData fd;


    @BeforeClass
    public static void beforeClass() {
        reader = new JSONReader();
        fd = reader.readFile(new File("src/test/java/exampleJSON.json"));
    }
}
