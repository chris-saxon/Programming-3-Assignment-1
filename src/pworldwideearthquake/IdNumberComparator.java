package pworldwideearthquake;

import java.util.Comparator;

/**
 * @author Chris_Saxon
 * @version 1.0
 */
public class IdNumberComparator implements Comparator<Earthquake> {
	
	// The IdNumberComparator uses the compare method to sort the Earthquake
	// ArrayList in descending order based on the Id Number.
	@Override
	public int compare(Earthquake e1, Earthquake e2) {
		return Integer.compare(e1.getIdNumber(), e2.getIdNumber());
	}

}
