package main.java.weathergen.dto;

import java.util.Arrays;

import main.java.weathergen.enums.ClimateType;
import main.java.weathergen.enums.HemispherePart;
import main.java.weathergen.utils.WeatherConfig;

/***
 * Class used to store envrionment conditions like latitude, longitude, etc.
 * @author vincentg
 *
 */
public class Environment {

	private double longitude;
	private double latitude;
	private double elevation;
	private ClimateType climate;
	private HemispherePart location;
	private String locationName;
	private double baseTemp;
	private double hPa;
	private WeatherConfig config;

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getElevation() {
		return elevation;
	}

	public ClimateType getClimate() {
		return climate;
	}

	public HemispherePart getLocation() {
		return location;
	}

	public String getLocationName() {
		return locationName;
	}

	public double getBaseTemp() {
		return baseTemp;
	}

	public double getHPa() {
		return hPa;
	}

	public WeatherConfig getConfig() {
		return config;
	}

	public Environment(String envData, WeatherConfig config) {
		try {

			String[] inputParts = parseInput(envData, "\\|");
			String[] locParts = parseInput(inputParts[1], ",");

			this.config = config;
			this.locationName = inputParts[0];
			this.latitude = Double.parseDouble(locParts[0]);
			this.longitude = Double.parseDouble(locParts[1]);
			this.elevation = Double.parseDouble(locParts[2]);
			this.climate = calcClimate();
			this.hPa = calcBasehPa();
			this.baseTemp = calcBaseTemp();
			this.location = getEquatorLocation();
		} catch (Exception e) {
			System.out.println("Error parsing input!");
			e.printStackTrace();
		}
	}

	private String[] parseInput(String input, String delim) throws Exception {
		String[] result = null;
		try {
			result = input.split(delim);
		} catch (Exception e) {
			System.out.println("Error parsing input: "+input);
			throw e;
		}
		return result;
	}


	/**
	 * Calculate hPa using the formula: <b>base_hPa*e^(elevation/height_of_earth_atmosphere)</b>
	 * @return hPa
	 */
	private double calcBasehPa() {
		return config.getBaseHpa() * Math.pow(
				config.getEConstant(),
				(this.elevation / config.getEarthAtmosphereHeight()) );
	}

	/**
	 * Calculate climate based on distance from equator.
	 * @return {@link ClimateType}
	 */
	private ClimateType calcClimate() {
		ClimateType climate = ClimateType.ARCTIC;
		if (latitude < 60 &&  latitude > -60 ) {
			if (latitude < 25 && latitude > -25) {
				climate = ClimateType.TROPICAL;
			} else {
				climate = ClimateType.TEMPERATE;
			}
		}
		return climate;
	}

	/***
	 * Calculate hemisphere given latitude
	 * @return {@link HemispherePart}
	 */
	private HemispherePart getEquatorLocation() {
		HemispherePart el;

		if (latitude > 0) {
			el = HemispherePart.NORTH;
		} else if (latitude < 0) {
			el = HemispherePart.SOUTH;
		} else {
			el = HemispherePart.CENTER;
		}
		return el;
	}

	/**
	 * get base temperature based on climate type
	 * @return {@link ClimateType}
	 */
	private double calcBaseTemp() {

		double baseTemp = config.getBaseTemperature();
		switch(climate) {
			case TROPICAL: {
				baseTemp = config.getClimateTropicalBaseTemp();
			} break;
			case TEMPERATE: {
				baseTemp = config.getClimateTemperateBaseTemp();
			} break;
			case ARCTIC: {
				baseTemp = config.getClimateArcticBaseTemp();
			} break;
		}
		return baseTemp;
	}
}
