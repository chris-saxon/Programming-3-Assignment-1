package pworldwideearthquake;

import java.io.*;
import java.util.ArrayList;

public class EarthquakeReader {
	public EarthquakeReader() {

	}

	/**
	 * The <b>load</b> method accepts an ArrayList of the <b>Earthquake</b> class, it then reads
	 * the contents of the CSV file and separates the Strings with a ",". The
	 * Strings are then converted to their correct Data types then are added
	 * into the ArrayList Earthquake.
	 * 
	 * @param earthquake
	 */
	// This method accepts an ArrayList of the Earthquake class, it then reads
	// the contents of the CSV file and separates the Strings with a ",". The
	// Strings are then converted to their correct Data types then are added
	// into the ArrayList Earthquake.
	public void load(ArrayList<Earthquake> earthquake) {

		// Data Fields
		int idNumber = 0;
		double magnitude;
		double depth;
		String date;
		String magType;
		float latitude;
		float longitude;
		String description;
		String line;

		try {
			// An instance of the BufferReader class.
			BufferedReader in = new BufferedReader(new FileReader(
					"csv/earthquakes.csv"));
			while ((line = in.readLine()) != null) {
				
				// The line.split separates the strings as they are being read
				// from the CSV file.
				String[] fields = line.split(",");
				
				// Each String is then converted to the correct data type.
				date = fields[0];
				latitude = Float.parseFloat(fields[1]);
				longitude = Float.parseFloat(fields[2]);
				depth = Double.parseDouble(fields[3]);
				magnitude = Double.parseDouble(fields[4]);
				magType = fields[5];
				description = fields[fields.length - 1];
				
				// Each row is added into the Earthquake ArrayList
				earthquake.add(new Earthquake(idNumber, date, magnitude,
						magType, depth, latitude, longitude, description));
				idNumber++;
			}
			in.close();
		} catch (NumberFormatException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

}