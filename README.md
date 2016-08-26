# weathergen

WeatherGen tries to determine Environment conditions given longitude, latitute, elevation and date, and tries to simulate what the weather would be like, given those conditions.

## build
mvn clean package shade:shade

## usage
java -jar weathergen-0.0.1-SNAPSHOT.jar {input_data} {config_file} {output_data}

### example
 java -jar weathergen-0.0.1-SNAPSHOT.jar ./input/input.txt ./config/weather.properties ./output.txt
