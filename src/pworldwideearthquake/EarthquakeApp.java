package pworldwideearthquake;

import java.util.ArrayList;

/**
 * @author Chris_Saxon
 * @version 1.0
 * 
 */
public class EarthquakeApp {

	/** Creates a new instance of the EarthquakeApp */
	public EarthquakeApp() {

	}

	/**
	 * The <b>main</b> method of the EarthquakeApp class creates and ArrayList of the
	 * Earthquake class. It then passes the ArrayList into the EarthquakeReader
	 * class.
	 * 
	 * @param args
	 *           
	 */
	// The main method of the EarthquakeApp class creates and ArrayList of the
	// Earthquake class. It then passes the ArrayList into the EarthquakeReader
	// class.
	public static void main(String[] args) {
		ArrayList<Earthquake> earthquake = new ArrayList<Earthquake>();
		EarthquakeReader files = new EarthquakeReader();
		files.load(earthquake);
		EarthquakeGui gui = new EarthquakeGui(earthquake);

	}

}
