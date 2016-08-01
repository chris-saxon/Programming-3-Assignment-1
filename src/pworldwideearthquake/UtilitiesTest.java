package pworldwideearthquake;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

/**
 * @author Chris_Saxon
 * @version 1.0
 */
public class UtilitiesTest {

	/**
	 * This method tests the <b>highestMagnitude</b> method by sorting through
	 * the Earthquake ArrayList and returns a double containing the highest
	 * magnitude.
	 */
	@Test
	public void testHighestMagnitude() {

		ArrayList<Earthquake> test = new ArrayList<Earthquake>();
		test.add(new Earthquake(1, "25-03-14", 6.3, "mb", 16, (float) 34.983,
				(float) 3.854, "Somewhere"));
		test.add(new Earthquake(1, "25-03-14", 9.1, "mb", 30, (float) 34.983,
				(float) 3.854, "Somewhere"));
		Utilities worker = new Utilities();
		double expected = 9.1;

		assertEquals(0, expected, worker.highestMagnitude(test));
	}

	/**
	 * This method tests the <b>lowestMagnitude</b> method by sorting through
	 * the Earthquake ArrayList then returns the lowest magnitude found.
	 */
	@Test
	public void testLowestMagnitude() {
		ArrayList<Earthquake> test = new ArrayList<Earthquake>();
		test.add(new Earthquake(1, "25-03-14", 6.3, "mb", 16, (float) 34.983,
				(float) 3.854, "Somewhere"));
		test.add(new Earthquake(1, "25-03-14", 9.1, "mb", 30, (float) 34.983,
				(float) 3.854, "Somewhere"));
		Utilities worker = new Utilities();
		double expected = 6.3;

		assertEquals(0, expected, worker.lowestMagnitude(test));
	}

	/**
	 * This method tests the <b>DeepestDepth</b> method by sorting through the
	 * Earthquake ArrayList then returns the greatest depth.
	 */
	@Test
	public void testDeepestDepth() {
		ArrayList<Earthquake> test = new ArrayList<Earthquake>();
		test.add(new Earthquake(1, "25-03-14", 6.3, "mb", 16, (float) 34.983,
				(float) 3.854, "Somewhere"));
		test.add(new Earthquake(1, "25-03-14", 9.1, "mb", 30, (float) 34.983,
				(float) 3.854, "Somewhere"));
		Utilities worker = new Utilities();
		double expected = 30;

		assertEquals(0, expected, worker.DeepestDepth(test));
	}

	/**
	 * This method tests the <b>ShortestDepth</b> method by sorting through the
	 * Earthquake ArrayList then returns the shortest depth.
	 */
	@Test
	public void testShortestDepth() {
		ArrayList<Earthquake> test = new ArrayList<Earthquake>();
		test.add(new Earthquake(1, "25-03-14", 6.3, "mb", 16, (float) 34.983,
				(float) 3.854, "Somewhere"));
		test.add(new Earthquake(1, "25-03-14", 9.1, "mb", 30, (float) 34.983,
				(float) 3.854, "Somewhere"));
		Utilities worker = new Utilities();
		double expected = 16;

		assertEquals(0, expected, worker.ShortestDepth(test));
	}

}
