package pworldwideearthquake;

import java.util.Comparator;

/**
 * @author Chris_Saxon
 * @version 1.0
 */
public class DepthComparator implements Comparator<Earthquake> {

	// The DepthComparator uses the compare method to sort the Earthquake
	// ArrayList in descending order based on the Depth.
	@Override
	public int compare(Earthquake e1, Earthquake e2) {

		return Double.compare(e1.getDepth(), e2.getDepth());
	}

}
