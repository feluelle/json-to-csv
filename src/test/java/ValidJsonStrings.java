import org.junit.Test;
import wrapper.JsonToCsvConverter;

import java.io.IOException;

public class ValidJsonStrings {
    private static final String
            jsonEmpty = "{}",
            csvEmpty = "";
    private static final String
            jsonField = "{\"field\":\"value\"}",
            csvField = "field\nvalue";
    private static final String
            jsonArray = "{\"field\":[1,2,3]}",
            csvArray = "field\n1\n2\n3";
    private static final String
            jsonObject = "{\"field\":{\"field-a\":4.0}}",
            csvObject = "field.field-a\n4.0";
    private static final String
            jsonAll = "{\"field1\":\"value1\",\"field2\":[1,2,3],\"field3\":{\"field3-a\":4.0},\"field4\":{},\"field5\":[4,5,6,7,8]}",
            csvAll = "field1;field2;field3.field3-a;field5\nvalue1;1;4.0;4\nvalue1;2;4.0;5\nvalue1;3;4.0;6\nvalue1;;4.0;7\nvalue1;;4.0;8";

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
