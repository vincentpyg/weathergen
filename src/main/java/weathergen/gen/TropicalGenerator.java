package main.java.weathergen.gen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import main.java.weathergen.dto.Environment;
import main.java.weathergen.enums.ClimateType;
import main.java.weathergen.enums.HemispherePart;
import main.java.weathergen.enums.SeasonType;
import main.java.weathergen.enums.WeatherType;
import main.java.weathergen.utils.WeatherConfig;

/**
 * Weather Generator that generates the weather for Tropical climates
 * @author vincentg
 *
 */
public class TropicalGenerator implements WeatherGeneratorIF {

	private SeasonType[] seasonLookup;
	private Environment env;
	private WeatherConfig config;

	public TropicalGenerator(Environment env) {
		this.env = env;
		this.config = env.getConfig();
		this.seasonLookup = new SeasonType[] {
				SeasonType.WET,
				SeasonType.DRY };
	}

	public String generateWeather(Calendar time) {
		SeasonType season = getSeason(
				env.getLocation(),
				env.getClimate(),
				time.get(Calendar.MONTH));

		double temp = genTemp(env.getBaseTemp(), season);
		double humidity = makeHumidity(season);

		WeatherType weather = makeWeather(humidity, temp);
		temp += genTempBonus(humidity, weather);
		temp += genTempBonus(weather);

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
				env.getLatitude(),
				env.getLongitude(),
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
	 * Estimate humidity based on season
	 * @param season Season
	 * @return Humidity
	 */
	private double makeHumidity(SeasonType season) {

		double humidity = config.getBaseHumidity();
		double humidityBonus = 0;

		if (season == SeasonType.DRY ) {
			humidityBonus = config.getHumidityBonusDry();
		} else if (season == SeasonType.WET) {
			humidityBonus = config.getHumidityBonusWet();
		}

		return humidity + humidityBonus;
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

	/***
	 * Generate temperature based on temperature and season.
	 * @param basetemp Base temperature of environment
	 * @param season {@link SeasonType}
	 * @return Temperature
	 */
	private double genTemp(double basetemp, SeasonType season) {

		double tempBonus = 0;
		if (season == SeasonType.DRY ) {
			tempBonus = config.getTempBonusDry();
		} else if (season == SeasonType.WET) {
			tempBonus = config.getTempBonusWet();
		}
		return basetemp + tempBonus;

	}

//	private void genTempBonus(Calendar calendar) {
//		//TODO:
//	}
	/**
	 * Generate bonuses to temperature based on elevation and weather
	 * @param elevation Elevation
	 * @param weather Weather
	 * @return
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

	/**
	 * Generate bonuses to temperature based on weather
	 * @param weather Weather
	 * @return Temperature bonus
	 */
	private double genTempBonus(WeatherType weather) {
		double tmpBonus = 0;
		switch (weather) {
			case RAIN: {
				tmpBonus = config.getTempBonusRain();
			} break;
			case SUNNY: {
				tmpBonus = config.getTempBonusSunny();
			} break;
			case SNOW: {
				tmpBonus = config.getTempBonusSnow();
			} break;
		}
		return tmpBonus;
	}

	/**
	 * Get season using hemisphere, month
	 * @param location
	 * @param climate
	 * @param month
	 * @return
	 */
	private SeasonType getSeason(HemispherePart location, ClimateType climate, int month) {

		SeasonType season = SeasonType.WET;
		switch (location) {
			case NORTH: {
				season = getSeason(month);
			}break;
			case SOUTH: {
				season = getSeason(month + 6);
			}break;
			case CENTER: {
				season = SeasonType.WET;
			}break;
		}
		return season;
	}

	/**
	 * Get current season from the lookup array, given the month
	 * @param month Month
	 * @return Season
	 */
	private SeasonType getSeason(int month) {
		return seasonLookup[((month + 8) / 6) % 2];
	}
}
