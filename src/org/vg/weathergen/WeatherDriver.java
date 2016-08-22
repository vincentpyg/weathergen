package org.vg.weathergen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.vg.weathergen.dto.Environment;
import org.vg.weathergen.gen.WeatherFactory;
import org.vg.weathergen.gen.WeatherGeneratorIF;
import org.vg.weathergen.utils.WeatherConfig;

public class WeatherDriver {

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			args = new String[] {
					"./test/input.txt",
					"./config/default.properties",
					"./test/output.txt" };
		} else {
			if (args.length != 3) {
				System.out.println("usage: java -jar <jar_file> <input> <config> <output>");
			}

		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(args[2])));

			String line = null;
			while ((line = br.readLine()) != null) {
				WeatherConfig config = new WeatherConfig(args[1]);
				WeatherFactory wfactory = new WeatherFactory();

				Environment e = new Environment(line.split("[|]"), config);
				WeatherGeneratorIF wg = wfactory.getWeatherGenerator(e);
				Calendar time = new GregorianCalendar();

				for (int x = 8; x < 40; x += 8) {
					bw.write(wg.generateWeather(time)+"\n");
					time.set(Calendar.HOUR, time.get(Calendar.HOUR) + (x * 2));
				}
			}
			bw.close();
			br.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
