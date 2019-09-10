package fr.diginamic.entites;

public class PositionGps {

	double latitude;
	double longitude;

	/**
	 * Constructeur
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public PositionGps(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setter
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Setter
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
