import org.junit.Test;

import java.io.IOException;

/**
 * Created by Felix on 09.04.2017.
 */
public class Tests {

    final String json = "{\"string\": \"test\", \"numbers\": [1, 2, 3]}";
    final String csv = "string;numbers\n"
            + "test;1\n"
            + "test;2\n"
            + "test;3\n";

    final String json1 = "{\"string\": \"test\", \"numbers\": [1, 2, 3], \"obj\": { \"a\": 4, \"b\": { \"c\": [5, 6, 7, 8] } }}";
    final String csv1 = "string;numbers;obj.a;obj.b.c\n"
            + "test;1;4;5\n"
            + "test;2;4;6\n"
            + "test;3;4;7\n"
            + "test;;4;8\n";

    final String json2 = "{\n" +
            "  \"Herausgeber\": \"Xema\",\n" +
            "  \"Nummer\": \"1234-5678-9012-3456\",\n" +
            "  \"Deckung\": 2e+6,\n" +
            "  \"Waehrung\": \"EURO\",\n" +
            "  \"Inhaber\":\n" +
            "  {\n" +
            "    \"Name\": \"Mustermann\",\n" +
            "    \"Vorname\": \"Max\",\n" +
            "    \"maennlich\": true,\n" +
            "    \"Hobbys\": [ \"Reiten\", \"Golfen\", \"Lesen\" ],\n" +
            "    \"Alter\": 42,\n" +
            "    \"Kinder\": [],\n" +
            "    \"Partner\": null\n" +
            "  }\n" +
            "}";
    final String csv2 = "Herausgeber;Nummer;Deckung;Waehrung;Inhaber.Name;Inhaber.Vorname;Inhaber.maennlich;Inhaber.Hobbys;Inhaber.Alter;Inhaber.Kinder;Inhaber.Partner\n"
            + "Xema;1234-5678-9012-3456;2000000.0;EURO;Mustermann;Max;true;Reiten;42;;null\n"
            + "Xema;1234-5678-9012-3456;2000000.0;EURO;Mustermann;Max;true;Golfen;42;;null\n"
            + "Xema;1234-5678-9012-3456;2000000.0;EURO;Mustermann;Max;true;Lesen;42;;null\n";

    @Test
    public void test() throws IOException {
        assert JsonToCsvConverter.jsonToCsv(json).equals(csv);
    }

    @Test
    public void test1() throws IOException {
        assert JsonToCsvConverter.jsonToCsv(json1).equals(csv1);
    }

    @Test
    public void test2() throws IOException {
        assert JsonToCsvConverter.jsonToCsv(json2).equals(csv2);
    }

}
