# weathergen

WeatherGen tries to determine Environment conditions given longitude, latitute and date, and tries to simulate what the Weather would be like in those conditions.

## build
mvn clean package shade:shade

## usage
java -jar original-weathergen-0.0.1-SNAPSHOT.jar <input_data> <config_file> <output_data>