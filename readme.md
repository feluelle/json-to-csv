# json-to-csv
Converts JSON to CSV without flatting it entirely.
So the array structure will be the same as before.

## How it works
The field's **largest value size** determines the **number of csv lines**.
* Writes values in each row as long as the largest size is not reached.
* The index of the array will be incremented after each row.
That means after an array size is reached there won't be further values in this column.

## Conditions
The json is **not allowed** to have
* an **array inside** an **array**.
* **different names** for **objects inside arrays**.

The JsonValidator used in this application validates both conditions to secure that the correct csv will be generated.
If any condition is false it will result in throwing an JsonInvalidFormatException.

## Example
This json..
```
{
    "field1": "value1",
    "field2": [1, 2, 3, 4, 5, 6, 7, 8, 9],
    "field3": {
        "field3-a": 4.0
    },
    "field4": { },
    "field5": [{
        "field5-a": 5,
        "field5-b": 5.1 
    }, {
        "field5-a": 6,
        "field5-b": 6.1 
    }, {
        "field5-a": 7,
        "field5-b": 7.1 
    }, {
        "field5-a": 8,
        "field5-b": 8.1 
    }, {
        "field5-a": 9,
        "field5-b": 9.1 
    }],
    "field6": [ ]
}
```
will be this csv..
```
field1;field2;field3.field3-a;field4;field5.field5-a;field5.field5-b;field6
value1;1;4.0;;5;5.1;
value1;2;4.0;;6;6.1;
value1;3;4.0;;7;7.1;
value1;4;4.0;;8;8.1;
value1;5;4.0;;9;9.1;
value1;6;4.0;;;;
value1;7;4.0;;;;
value1;8;4.0;;;;
value1;9;4.0;;;;
```
displayed as a table..

field1 | field2 | field3.field3-a | field4 | field5.field5-a | field5.field5-b | field6
---|---|---|---|---|---|---
value1 | 1 | 4.0 | | 5 | 5.1 |
value1 | 2 | 4.0 | | 6 | 6.1 |
value1 | 3 | 4.0 | | 7 | 7.1 |
value1 | 4 | 4.0 | | 8 | 8.1 |
value1 | 5 | 4.0 | | 9 | 9.1 |
value1 | 6 | 4.0 | |   |     |
value1 | 7 | 4.0 | |   |     |
value1 | 8 | 4.0 | |   |     |
value1 | 9 | 4.0 | |   |     |

by only executing this command:
```
String csv = JsonToCsvConverter.jsonToCsv(json);
```

## Dependencies

### json
This library uses com.fasterxml.jackson.core's [jackson-databind](https://github.com/FasterXML/jackson-databind) to deserialize json to a map.

## Misc

[jsonlint](https://jsonlint.com/) is used to test if the json validation in this library works.