package weathergen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import weathergen.dto.Environment;
import weathergen.gen.WeatherFactory;
import weathergen.gen.WeatherGeneratorIF;
import weathergen.utils.WeatherConfig;

public class WeatherDriver {

	public static void main(String[] args) {


		try {

			if (args.length != 3) {
				System.out.println("usage: java -jar <jar_file> <input> <config> <output>");
				return;
			}

			BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(args[2])));

			WeatherConfig config = new WeatherConfig(new File(args[1]));

			String line = null;
			while ((line = br.readLine()) != null) {

				WeatherFactory wfactory = new WeatherFactory();

				Environment e = new Environment(line, config);
				WeatherGeneratorIF wg = wfactory.getWeatherGenerator(e);
				Calendar time = new GregorianCalendar();

				for (int x = 0; x < 40; x += 8) {
					bw.write(wg.generateWeather(time)+"\n");
					time.set(Calendar.HOUR, time.get(Calendar.HOUR) + (x * 2));
				}
			}
			bw.close();
			br.close();

		} catch (Exception e1) {
			System.out.println("Error occurred! check input file or usage.");
			System.out.println( getUsageString() );
		}
	}

	public static String getUsageString() {
		return "usage: java -jar <jar_file> <input> <config> <output>";
	}
}
