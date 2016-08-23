package main.java.weathergen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/***
 * Used for parsing a configuration file containing assumptions for generating the weather..
 * Defaults to {@link WeatherGeneratorConstants} when there is a problem loading the config file
 * @author vincentg
 *
 */
public class WeatherConfig {

	private Properties wProps = null;

	public WeatherConfig(File propertyFile) {
		wProps = new Properties();
		try {
				wProps.load(new FileInputStream(propertyFile));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public Properties getConfig() {
		return wProps;
	}

	public double getBaseTemperature() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("base_temperature"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.BASE_TEMPERATURE;
			e.printStackTrace();
		}
		return result;
	}

	public double getBaseHumidity() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("base_humidity"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.BASE_HUMIDITY;
			e.printStackTrace();
		}
		return result;
	}

	public double getBaseHpa() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("base_hpa"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.BASE_HPA;
			e.printStackTrace();
		}
		return result;
	}

	public double getEConstant() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("e_constant"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.E_CONSTANT;
			e.printStackTrace();
		}
		return result;
	}

	public double getEarthAtmosphereHeight() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("earth_atmosphere_height"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.EARTH_ATMOSPHERE_HEIGHT;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSunnyUnit() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_sunny_unit",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SUNNY_UNIT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSunnyChange() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_sunny_change",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SUNNY_CHANGE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSnowyUnit() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_snowy_unit",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SNOWY_UNIT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSnowyChange() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_snowy_change",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SNOWY_CHANGE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getClimateArcticBaseTemp() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("climate_arctic_base_temp"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.CLIMATE_ARCTIC_BASE_TEMP;
			e.printStackTrace();
		}
		return result;
	}

	public double getClimateTemperateBaseTemp() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("climate_temperate_base_temp"));

		} catch (Exception e) {
			result = WeatherGeneratorConstants.CLIMATE_TEMPERATE_BASE_TEMP;
			e.printStackTrace();
		}
		return result;
	}

	public double getClimateTropicalBaseTemp() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("climate_tropical_base_temp"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.CLIMATE_TROPICAL_BASE_TEMP;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusWinter() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_winter"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_WINTER;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSpring() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_spring"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SPRING;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSummer() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_summer"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SUMMER;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusFall() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_fall"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_FALL;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusWet() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_bonus_wet"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_WET;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusDry() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_bonus_dry"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_DRY;
			e.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusWinter() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("humidity_bonus_winter"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_WINTER;
			e.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusSpring() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("humidity_bonus_spring"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_SPRING;
			e.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusSummer() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("humidity_bonus_summer"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_SUMMER;
			e.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusFall() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("humidity_bonus_fall"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_FALL;
			e.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusWet() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("humidity_bonus_wet"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_WET;
			e.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusDry() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("humidity_bonus_dry"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_DRY;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusRain() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_rain"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_RAIN;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSnow() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_snow"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SNOW;
			e.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSunny() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_sunny"));
		} catch (Exception e) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SUNNY;
			e.printStackTrace();
		}
		return result;
	}
}
