package main.java.weathergen.gen;

import main.java.weathergen.dto.Environment;

/***
 * Factory class for generating different types of weather generators
 * @author vincentg
 *
 */
public class WeatherFactory {

	/***
	 * Creates a weather generator based on the environment
	 * @param env Environment
	 * @return Weather generator
	 */
	public WeatherGeneratorIF getWeatherGenerator(Environment env) {

		WeatherGeneratorIF wgen = null;

		switch (env.getClimate()) {
			case TEMPERATE: {
				wgen = new TemperateGenerator(env);
			} break;
			case TROPICAL: {
				wgen = new TropicalGenerator(env);
			} break;
			case ARCTIC: {
				wgen = new ArcticGenerator(env);
			}
		}
		return wgen;
	}
}
