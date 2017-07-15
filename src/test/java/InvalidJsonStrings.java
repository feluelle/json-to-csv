import org.junit.Test;
import util.validator.InvalidJsonFormatException;
import wrapper.JsonToCsvConverter;

public class InvalidJsonStrings {
    private static final String
            jsonInvalid = "{\"field1\":\"value1\",\"field2\":[1,2,3],field3\":{\"field4\":4.0},\"field5\":{}}";

    private static final String
            jsonInvalidArrayInsideArray = "{\"field1\":{\"field1-a\":[[1]]}}";

    private static final String
            jsonInvalidObjectNamesInArray = "{" +
            "\"field5\":[" +
            "{\"field5-a\":5,\"field5-b\":5.1}," +
            "{\"field5-c\":6,\"field5-d\":6.1}," +
            "]" +
            "}";

    @Test(expected = InvalidJsonFormatException.class)
    public void invalidTest() throws InvalidJsonFormatException {
        JsonToCsvConverter
                .jsonToCsv(jsonInvalid);
    }

    @Test(expected = InvalidJsonFormatException.class)
    public void invalidArrayInsideArrayTest() throws InvalidJsonFormatException {
        JsonToCsvConverter
                .jsonToCsv(jsonInvalidArrayInsideArray);
    }

    @Test(expected = InvalidJsonFormatException.class)
    public void invalidObjectNamesInArrayTest() throws InvalidJsonFormatException {
        JsonToCsvConverter
                .jsonToCsv(jsonInvalidObjectNamesInArray);
    }
}
