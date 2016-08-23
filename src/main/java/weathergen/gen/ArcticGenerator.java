package main.java.weathergen.gen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import main.java.weathergen.dto.Environment;
import main.java.weathergen.enums.SeasonType;
import main.java.weathergen.enums.WeatherType;
import main.java.weathergen.utils.WeatherConfig;

/**
 * Weather Generator that generates the weather for Arctic climates
 * @author vincentg
 *
 */
public class ArcticGenerator implements WeatherGeneratorIF {

	private Environment env;
	private WeatherConfig config;

	public ArcticGenerator(Environment env) {
		this.env = env;
		this.config = env.getConfig();
	}

	public String generateWeather(Calendar time) {
		SeasonType season = SeasonType.WINTER;

		double temp = genTemp(env.getBaseTemp(), season);
		double humidity = 0.7;

		WeatherType weather = makeWeather(humidity, temp);
		temp += genTempBonus(humidity, weather);

		return formatOutputString(time, weather, temp, humidity);
	}

	/**
	 * Format weather data based on output format
	 * @param time Time
	 * @param weather Weather
	 * @param temp Temperature
	 * @param humidity Humidity
	 * @return Formatted string
	 */
	private String formatOutputString(
			Calendar time, WeatherType weather, double temp, double humidity) {

		return String.format("%s|%.2f,%.2f,%.2f|%s|%s|%.2f|%.2f|%.0f",
				env.getLocationName(),
				env.getLongitude(),
				env.getLatitude(),
				env.getElevation(),
				formatTime(time),
				weather,
				temp,
				env.getHPa(),
				humidity*100); //get percentage
	}

	/**
	 * Format time using ISO8601 standard
	 * @param time Time
	 * @return Time in ISO8601 format
	 */
	private String formatTime(Calendar time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		time.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(time.getTime());
	}

	/***
	 * Generate weather conditions based on season humidity and temperature
	 * @param humidity Humidity
	 * @param temp Temperature
	 * @return {@link WeatherType}
	 */
	private WeatherType makeWeather(double humidity, double temp) {

		WeatherType w;

		Random rand = new Random();
		double per = rand.nextInt(100);

		if (per <= (int)(humidity * 100)) {
			w = (temp < 0) ? WeatherType.SNOW : WeatherType.RAIN;
		} else {
			w = WeatherType.SUNNY;
		}
		return w;
	}

//	private void genTempBonus(Calendar calendar) {
//		//TODO: generate bonus temp based on time of day
//	}

	/***
	 * Generate temperature based on temperature and season.
	 * It is assumed that the Arctic climate only has 1 season, {@link SeasonType#WINTER}.
	 * @param basetemp Base temperature of environment
	 * @param season {@link SeasonType}
	 * @return Temperature
	 */
	private double genTemp(double basetemp, SeasonType season) {
		return config.getTempBonusWinter();
	}

	/**
	 * Generate bonuses to temperature based on elevation and weather
	 * @param elevation Elevation
	 * @param weather Weather
	 * @return Temperature bonus
	 */
	private double genTempBonus(double elevation, WeatherType weather) {
		double tmpBonus = 0;
		switch (weather) {
			case RAIN: { }
			case SUNNY: {
				tmpBonus = (elevation / 305) * -3;
			}break;
			case SNOW: {
				tmpBonus = (elevation / 305) * -1.83;
			}break;
		}
		return tmpBonus;
	}
}
