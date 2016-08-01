package pworldwideearthquake;

/**
 * @author Chris_Saxon
 * @version 1.0
 * 
 */

public class Earthquake implements Comparable<Earthquake> {

	// Complex Object Data Fields
	/**
	 * A String containing the date of the earthquake.
	 */
	private String date;
	/**
	 * The magnitude type of the earthquake.
	 */
	private String magType;
	/**
	 * A String containing a brief description of the location of the
	 * earthquake.
	 */
	private String description;

	// Primitive Data Fields
	private int idNumber;
	private double magnitude;
	private double depth;
	private float latitude;
	private float longitude;

	/**
	 * This is the constructor for the Earthquake class it takes the following
	 * parameters and assigns them to the data fields within the class.
	 * 
	 * @param idNumber
	 * @param date
	 * @param magnitude
	 * @param magType
	 * @param depth
	 * @param latitude
	 * @param longitude
	 * @param description
	 */
	public Earthquake(int idNumber, String date, double magnitude,
			String magType, double depth, float latitude, float longitude,
			String description) {
		setDate(date);
		setDepth(depth);
		setIdNumber(idNumber);
		setMagnitude(magnitude);
		setMagtype(magType);
		setLatitude(latitude);
		setLongitude(longitude);
		setDescription(description);
	}

	// Modifiers
	/**
	 * This is the setter for the primitive <b>idNumber</b>
	 * 
	 * @param idNumber
	 */

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * This is the setter method for the primitive <b>Date</b>
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Sets the value of magnitude.
	 * 
	 * @param magnitude
	 */
	public void setMagnitude(double magnitude) {
		this.magnitude = magnitude;
	}

	/**
	 * @param magType
	 */
	public void setMagtype(String magType) {
		this.magType = magType;
	}

	/**
	 * @param depth
	 */
	public void setDepth(double depth) {
		this.depth = depth;
	}

	/**
	 * @param latitude
	 *            , sets the latitude.
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return The allocated ID Number of an earthquake.
	 */
	public int getIdNumber() {
		return idNumber;
	}

	/**
	 * @return The date of an earthquake.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return A double containing the magnitude.
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * This is the getter method for the MagType String.
	 * 
	 * @return magType.
	 */
	public String getMagType() {
		return magType;
	}

	/**
	 * @return The depth of an earthquake as a double.
	 */
	public double getDepth() {
		return depth;
	}

	/**
	 * @return The latitude of an earthquake.
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * @return The longitude of an earthquake.
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * @return A brief description of the location of earthquakes.
	 */
	public String getDescription() {
		return description;
	}

	public String toString() {
		return "Id: " + getIdNumber() + " date: " + getDate() + " Magnitude: "
				+ getMagnitude() + " MagType: " + getMagType() + " Depth: "
				+ getDepth() + " Latitude: " + getLatitude() + " Longitude: "
				+ getLongitude() + " Description: " + getDescription() + "\n";

	}

	@Override
	public int compareTo(Earthquake earthquake) {
		return this.toString().compareTo(earthquake.toString());

	}


}
