package pworldwideearthquake;

import java.util.Comparator;

/**
 * @author Chris_Saxon
 * @version 1.0
 */
public class MagnitudeComparator implements Comparator<Earthquake> {
	
	// The MagnitudeComparator uses the compare method to sort the Earthquake
	// ArrayList in descending order based on the Magnitude.
	@Override
	public int compare(Earthquake e1, Earthquake e2) {
		return Double.compare(e1.getMagnitude(), e2.getMagnitude());
	}

}
