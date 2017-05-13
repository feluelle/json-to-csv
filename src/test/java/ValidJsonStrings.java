import org.junit.Test;
import wrapper.JsonToCsvConverter;

import java.io.IOException;

public class ValidJsonStrings {
    private static final String
            jsonEmpty = "{}",
            csvEmpty = "";
    private static final String
            jsonField = "{\"field\":\"value\"}",
            csvField = "field\nvalue\n";
    private static final String
            jsonArray = "{\"field\":[1,2,3]}",
            csvArray = "field\n1\n2\n3\n";
    private static final String
            jsonObject = "{\"field\":{\"innerField\":4.0}}",
            csvObject = "field.innerField\n4.0\n";
    private static final String
            jsonAll = "{\"field1\":\"value1\",\"field2\":[1,2,3],\"field3\":{\"field4\":4.0},\"field5\":{}}",
            csvAll = "field1;field2;field3.field4\nvalue1;1;4.0\nvalue1;2;4.0\nvalue1;3;4.0\n";

    @Test
    public void emptyTest() throws IOException {
        assert JsonToCsvConverter
                .jsonToCsv(jsonEmpty)
                .equals(csvEmpty);
    }

    @Test
    public void fieldTest() throws IOException {
        assert JsonToCsvConverter
                .jsonToCsv(jsonField)
                .equals(csvField);
    }

    @Test
    public void arrayTest() throws IOException {
        assert JsonToCsvConverter
                .jsonToCsv(jsonArray)
                .equals(csvArray);
    }

    @Test
    public void objectTest() throws IOException {
        assert JsonToCsvConverter
                .jsonToCsv(jsonObject)
                .equals(csvObject);
    }

    @Test
    public void allTest() throws IOException {
        assert JsonToCsvConverter
                .jsonToCsv(jsonAll)
                .equals(csvAll);
    }
}