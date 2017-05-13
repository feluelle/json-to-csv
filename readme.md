# json-to-csv
Converts JSON to CSV

## Notes
The **biggest array size** determines the **number of csv lines**.
* Ignores empty json objects
* Writes the same object in every row
* The index of the array will be incremented after each row

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
    "field5": [4, 5, 6, 7, 8]
}
```
will be this csv..
```
field1;field2;field3.field3-a;field5
value1;1;4.0;4
value1;2;4.0;5
value1;3;4.0;6
value1;;4.0;7
value1;;4.0;8
```

## Dependencies

### json
This library uses com.fasterxml.jackson.core's jackson-databind with version 2.9.0.pr3 
to deserialize json to a map. But I could probably use any version. 
I also tested it out with version 2.0.0.-RC1 (oldest version in mvn central) and 2.8.8.1 (current latest release).

However it does not matter for you because you do not have to add this dependecy by yourself.