package weathergen.gen;

import java.util.Calendar;

public interface WeatherGeneratorIF {
	/**
	 * Generates the weather for a given time and returns it as a formatted string
	 * @param time
	 * @return weather
	 */
	public String generateWeather(Calendar time);

}
