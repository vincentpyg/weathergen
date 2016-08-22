package org.vg.weathergen.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WeatherConfig {

	private Properties wProps = null;

	public WeatherConfig(String propertyFile) {
		wProps = new Properties();
		try {
			if (propertyFile == null) {
				wProps.load(this.getClass().getResourceAsStream(propertyFile));
			} else {
				wProps.load(new FileInputStream(new File(propertyFile)));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			wProps = null;
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
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.BASE_TEMPERATURE;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getBaseHumidity() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("base_humidity"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.BASE_HUMIDITY;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getBaseHpa() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("base_hpa"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.BASE_HPA;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getEConstant() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("e_constant"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.E_CONSTANT;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getEarthAtmosphereHeight() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("earth_atmosphere_height"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.EARTH_ATMOSPHERE_HEIGHT;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSunnyUnit() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_sunny_unit",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SUNNY_UNIT));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSunnyChange() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_sunny_change",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SUNNY_CHANGE));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSnowyUnit() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_snowy_unit",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SNOWY_UNIT));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempElevationChangeSnowyChange() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_elevation_change_snowy_change",
					"" + WeatherGeneratorConstants.TEMP_ELEVATION_CHANGE_SNOWY_CHANGE));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return result;
	}

	public double getClimateArcticBaseTemp() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("climate_arctic_base_temp"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.CLIMATE_ARCTIC_BASE_TEMP;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getClimateTemperateBaseTemp() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("climate_temperate_base_temp"));

		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.CLIMATE_TEMPERATE_BASE_TEMP;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getClimateTropicalBaseTemp() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("climate_tropical_base_temp"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.CLIMATE_TROPICAL_BASE_TEMP;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusWinter() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_winter"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_WINTER;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSpring() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_spring"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SPRING;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSummer() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_summer"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SUMMER;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusFall() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_fall"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_FALL;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusWet() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_bonus_wet"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_WET;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusDry() {
		double result = 0.0;
		try {
			result = Double.parseDouble(wProps.getProperty("temp_bonus_dry"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_DRY;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusWinter() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("humidity_bonus_winter"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_WINTER;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusSpring() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("humidity_bonus_spring"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_SPRING;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusSummer() {
		double result = 0.0;
		try {
			result = Double.parseDouble(
					wProps.getProperty("humidity_bonus_summer"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_SUMMER;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusFall() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("humidity_bonus_fall"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_FALL;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusWet() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("humidity_bonus_wet"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_WET;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getHumidityBonusDry() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("humidity_bonus_dry"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.HUMIDITY_BONUS_DRY;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusRain() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_rain"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_RAIN;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSnow() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_snow"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SNOW;
			nfe.printStackTrace();
		}
		return result;
	}

	public double getTempBonusSunny() {
		double result = 0.0;
		try {
			result = Double
					.parseDouble(wProps.getProperty("temp_bonus_sunny"));
		} catch (NumberFormatException nfe) {
			result = WeatherGeneratorConstants.TEMP_BONUS_SUNNY;
			nfe.printStackTrace();
		}
		return result;
	}
}
