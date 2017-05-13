import org.junit.Test;
import wrapper.JsonToCsvConverter;

import java.io.IOException;

public class InvalidJsonStrings {
    private static final String
            jsonInvalid = "{\"field1\":\"value1\",\"field2\":[1,2,3],field3\":{\"field4\":4.0},\"field5\":{}}";

    @Test(expected = IOException.class)
    public void invalidTest() throws IOException {
        JsonToCsvConverter
                .jsonToCsv(jsonInvalid);
    }
}
