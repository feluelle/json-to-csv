# json-to-csv
Converts JSON to CSV

## Notes
The field's **biggest value size** determines the **number of csv lines**.
* Writes values in each row as long as the biggest size is not reached.
* The index of the array will be incremented after each row.
That means after an array size is reached there won't be further values in this column.

**IMPORTANT**: Each object in an array has to contain the same fields.

## Example
This json..
```
{
    "field1": "value1",
    "field2": [1, 2, 3],
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
value1;;4.0;;8;8.1;
value1;;4.0;;9;9.1;
```

## Dependencies

### json
This library uses com.fasterxml.jackson.core's jackson-databind to deserialize json to a map.