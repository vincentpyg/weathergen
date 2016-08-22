package org.vg.weathergen.gen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import org.vg.weathergen.dto.Environment;
import org.vg.weathergen.enums.Season;
import org.vg.weathergen.enums.Weather;
import org.vg.weathergen.utils.WeatherConfig;

public class ArcticGenerator implements WeatherGeneratorIF {

	private Environment env;
	private WeatherConfig config;

	public ArcticGenerator(Environment env) {
		this.env = env;
		this.config = env.getConfig();
	}

	public String generateWeather(Calendar time) {
		Season season = Season.WINTER;

		double temp = genTemp(env.getBaseTemp(), season);
		double humidity = 0.7;

		Weather weather = makeWeather(humidity, temp);
		temp += genTempBonus(humidity, weather);

		return formatOutputString(time, weather, temp, humidity);
	}


	private String formatOutputString(
			Calendar time, Weather weather, double temp, double humidity) {

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

	private String formatTime(Calendar time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		time.setTimeZone(TimeZone.getTimeZone("UTC"));
		return df.format(time.getTime());
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

//	private void genTempBonus(Calendar calendar) {
//		//TODO: generate bonus temp based on time of day
//	}

	private double genTemp(double basetemp, Season season) {
		return config.getTempBonusWinter();
	}

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
}
