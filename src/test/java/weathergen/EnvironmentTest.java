package weathergen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import weathergen.dto.Environment;
import weathergen.enums.ClimateType;
import weathergen.enums.HemispherePart;
import weathergen.utils.WeatherConfig;

public class EnvironmentTest {

	@Test
	public void testTemperateFlow() {

		String testInput="Ottawa|45.25,75.1,70";

		try {

			InputStream weatherConfIS = this.getClass().getClassLoader().getResourceAsStream("weather.properties");
			WeatherConfig conf = new WeatherConfig( createTempFile(weatherConfIS) );
			weatherConfIS.close();

			Environment env = new Environment(testInput, conf);

			Assert.assertEquals(conf, env.getConfig());
			Assert.assertEquals(25.0, env.getBaseTemp(),0);
			Assert.assertEquals(ClimateType.TEMPERATE, env.getClimate());
			Assert.assertEquals(70.0, env.getElevation(),0);
			Assert.assertEquals(1023.4397986921529, env.getHPa(),0);
			Assert.assertEquals(45.25,env.getLatitude(),0);
			Assert.assertEquals(75.1,env.getLongitude(),0);
			Assert.assertEquals(HemispherePart.NORTH, env.getLocation());
			Assert.assertEquals("Ottawa", env.getLocationName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testTropicalFlow() {

		String testInput="Manila|14.35,121.00,16";

		try {

			InputStream weatherConfIS = this.getClass().getClassLoader().getResourceAsStream("weather.properties");
			WeatherConfig conf = new WeatherConfig( createTempFile(weatherConfIS) );
			weatherConfIS.close();

			Environment env = new Environment(testInput, conf);

			Assert.assertEquals(conf, env.getConfig());
			Assert.assertEquals(30.0, env.getBaseTemp(),0);
			Assert.assertEquals(ClimateType.TROPICAL, env.getClimate());
			Assert.assertEquals(16.0, env.getElevation(),0);
			Assert.assertEquals(1015.5701156596208, env.getHPa(),0);
			Assert.assertEquals(14.35,env.getLatitude(),0);
			Assert.assertEquals(121.00,env.getLongitude(),0);
			Assert.assertEquals(HemispherePart.NORTH, env.getLocation());
			Assert.assertEquals("Manila", env.getLocationName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testArcticFlow() {

		String testInput="Alaska|64.45,147.21,147";

		try {

			InputStream weatherConfIS = this.getClass().getClassLoader().getResourceAsStream("weather.properties");
			WeatherConfig conf = new WeatherConfig( createTempFile(weatherConfIS) );
			weatherConfIS.close();

			Environment env = new Environment(testInput, conf);

			Assert.assertEquals(conf, env.getConfig());
			Assert.assertEquals(0, env.getBaseTemp(),0);
			Assert.assertEquals(ClimateType.ARCTIC, env.getClimate());
			Assert.assertEquals(147, env.getElevation(),0);
			Assert.assertEquals(1034.7669745569901, env.getHPa(),0);
			Assert.assertEquals(64.45,env.getLatitude(),0);
			Assert.assertEquals(147.21,env.getLongitude(),0);
			Assert.assertEquals(HemispherePart.NORTH, env.getLocation());
			Assert.assertEquals("Alaska", env.getLocationName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}




	private File createTempFile(InputStream is) {

		File tempFile = null;
		try {
			tempFile = File.createTempFile("wprop", "tmp");
			tempFile.deleteOnExit();
			FileOutputStream out = new FileOutputStream(tempFile);
			IOUtils.copy(is, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			return tempFile;
	}
}
