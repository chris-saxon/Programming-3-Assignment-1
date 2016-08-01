package pworldwideearthquake;

import java.util.ArrayList;

/**
 * @author Chris_Saxon
 * @version 1.0
 */
public class Utilities {

	/**
	 * @param earthquakes
	 * @return max
	 */
	public double highestMagnitude(ArrayList<Earthquake> earthquakes) {
		double max = earthquakes.get(0).getMagnitude();
		for (int i = 0; i < earthquakes.size(); i++) {
			if (earthquakes.get(i).getMagnitude() > max) {
				max = earthquakes.get(i).getMagnitude();
			}

		}
		return max;
	}

	/**
	 * This method accepts an ArrayList of Earthquake then sorts through the
	 * ArrayList comparing the magnitude to find and return the smallest
	 * magnitude.
	 * 
	 * @param earthquakes
	 * @return min
	 */
	public double lowestMagnitude(ArrayList<Earthquake> earthquakes) {
		double min = earthquakes.get(0).getMagnitude();
		for (int i = 0; i < earthquakes.size(); i++) {
			if (earthquakes.get(i).getMagnitude() < min) {
				min = earthquakes.get(i).getMagnitude();
			}

		}
		return min;
	}

	/**
	 * The <b>DeepestDepth</b> method accepts and ArrayList<> then sorts
	 * through the array comparing the depth to return the maximum.
	 * 
	 * @param earthquakes
	 * @return max
	 */
	public double DeepestDepth(ArrayList<Earthquake> earthquakes) {
		double max = earthquakes.get(0).getDepth();
		for (int i = 0; i < earthquakes.size(); i++) {
			if (earthquakes.get(i).getDepth() > max) {
				max = earthquakes.get(i).getDepth();
			}

		}
		return max;
	}

	/**
	 * The <b>ShortestDepth</b> method accepts and ArrayList<> then sorts
	 * through the array comparing the depth to return the minimum.
	 * 
	 * @param earthquakes
	 * @return min
	 */
	public double ShortestDepth(ArrayList<Earthquake> earthquakes) {
		double min = earthquakes.get(0).getDepth();
		for (int i = 0; i < earthquakes.size(); i++) {
			if (earthquakes.get(i).getDepth() < min) {
				min = earthquakes.get(i).getDepth();
			}

		}
		return min;
	}

}