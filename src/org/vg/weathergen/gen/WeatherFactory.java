package org.vg.weathergen.gen;

import org.vg.weathergen.dto.Environment;

public class WeatherFactory {

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
