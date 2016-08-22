package org.vg.weathergen.gen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import org.vg.weathergen.dto.Environment;
import org.vg.weathergen.enums.Climate;
import org.vg.weathergen.enums.Hemisphere;
import org.vg.weathergen.enums.Season;
import org.vg.weathergen.enums.Weather;
import org.vg.weathergen.utils.WeatherConfig;

public class TropicalGenerator implements WeatherGeneratorIF {

	private Season[] seasonLookup;
	private Environment env;
	private WeatherConfig config;

	public TropicalGenerator(Environment env) {
		this.env = env;
		this.config = env.getConfig();
		this.seasonLookup = new Season[] {
				Season.WET,
				Season.DRY };
	}

	public String generateWeather(Calendar time) {
		Season season = getSeason(
				env.getLocation(),
				env.getClimate(),
				time.get(Calendar.MONTH));

		double temp = genTemp(env.getBaseTemp(), season);
		double humidity = makeHumidity(season);

		Weather weather = makeWeather(humidity, temp);
		temp += genTempBonus(humidity, weather);
		temp += genTempBonus(weather);

		return formatOutputString(time, weather, temp, humidity);
	}


	private String formatOutputString(
			Calendar time, Weather weather, double temp, double humidity) {

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

	private String formatTime(Calendar time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		time.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(time.getTime());
	}

	private double makeHumidity(Season season) {

		double humidity = config.getBaseHumidity();
		double humidityBonus = 0;

		if (season == Season.DRY ) {
			humidityBonus = config.getHumidityBonusDry();
		} else if (season == Season.WET) {
			humidityBonus = config.getHumidityBonusWet();
		}

		return humidity + humidityBonus;
	}

	private Weather makeWeather(double humidity, double temp) {

		Weather w;

		Random rand = new Random();
		double per = rand.nextInt(100);

		if (per <= (int)(humidity * 100)) {
			w = (temp < 0) ? Weather.SNOW : Weather.RAIN;
		} else {
			w = Weather.SUNNY;
		}
		return w;
	}

	private double genTemp(double basetemp, Season season) {

		double tempBonus = 0;
		if (season == Season.DRY ) {
			tempBonus = config.getTempBonusDry();
		} else if (season == Season.WET) {
			tempBonus = config.getTempBonusWet();
		}
		return basetemp + tempBonus;

	}

//	private void genTempBonus(Calendar calendar) {
//		//TODO:
//	}

	private double genTempBonus(double elevation, Weather weather) {

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

	private double genTempBonus(Weather weather) {
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

	private Season getSeason(Hemisphere location, Climate climate, int month) {

		Season season = Season.WET;
		switch (location) {
			case NORTH: {
				season = getSeason(month);
			}break;
			case SOUTH: {
				season = getSeason(month + 6);
			}break;
			case CENTER: {
				season = Season.WET;
			}break;
		}
		return season;
	}

	private Season getSeason(int month) {
		return seasonLookup[((month + 8) / 6) % 2];
	}
}
