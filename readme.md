# json-to-csv
Converts JSON to CSV

## Notes
The field's **biggest value size** determines the **number of csv lines**.
* Writes values in each row as long as the biggest size is not reached.
* The index of the array will be incremented after each row.
That means after an array size is reached there won't be further values in this column.

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
        "field5-a": 5.1 
    }, {
        "field5-b": 6
    }, {
        "field5-c": 7
    }, {
        "field5-d": 8
    }, {
        "field5-e": 9
    }],
    "field6": [ ]
}
```
will be this csv..
```
field1;field2;field3.field3-a;field4;field5.field5-a;field6
value1;1;4.0;;5.1;
value1;2;4.0;;6;
value1;3;4.0;;7;
value1;;4.0;;8;
value1;;4.0;;9;
```

## Dependencies

### json
This library uses com.fasterxml.jackson.core's jackson-databind to deserialize json to a map.