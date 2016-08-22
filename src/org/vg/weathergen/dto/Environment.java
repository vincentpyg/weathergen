package org.vg.weathergen.dto;

import org.vg.weathergen.enums.Climate;
import org.vg.weathergen.enums.Hemisphere;
import org.vg.weathergen.utils.WeatherConfig;

public class Environment {

	private double longitude;
	private double latitude;
	private double elevation;
	private Climate climate;
	private Hemisphere location;
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

	public Climate getClimate() {
		return climate;
	}

	public Hemisphere getLocation() {
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

	public Environment(String[] envData, WeatherConfig config) {

		this.config = config;
		this.locationName = envData[0];
		String[] locParts = envData[1].split(",");
		this.latitude = Double.parseDouble(locParts[0]);
		this.longitude = Double.parseDouble(locParts[1]);
		this.elevation = Double.parseDouble(locParts[2]);
		this.climate = calcClimate();
		this.hPa = calcBasehPa();
		this.baseTemp = calcBaseTemp();
		this.location = getEquatorLocation();


	}

	private double calcBasehPa() {
		return config.getBaseHpa() * Math.pow(
				config.getEConstant(),
				(this.elevation / config.getEarthAtmosphereHeight()) );
	}

	private Climate calcClimate() {
		Climate climate = Climate.ARCTIC;
		if (latitude < 60 &&  latitude > -60 ) {
			if (latitude < 25 && latitude > -25) {
				climate = Climate.TROPICAL;
			} else {
				climate = Climate.TEMPERATE;
			}
		}
		return climate;
	}

	private Hemisphere getEquatorLocation() {
		Hemisphere el;

		if (latitude > 0) {
			el = Hemisphere.NORTH;
		} else if (latitude < 0) {
			el = Hemisphere.SOUTH;
		} else {
			el = Hemisphere.CENTER;
		}
		return el;
	}

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
