# json-to-csv
Converts JSON to CSV

## Dependencies

### json
This library uses com.fasterxml.jackson.core's jackson-databind with version 2.9.0.pr3 
to deserialize json to a map. But I could probably use any version. 
I also tested it out with version 2.0.0.-RC1 (oldest version in mvn central) and 2.8.8.1 (current latest release).

However it does not matter for you because you do not have to add this dependecy by yourself.