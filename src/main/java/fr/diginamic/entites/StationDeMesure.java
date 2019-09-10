package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class StationDeMesure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long idPrincipal;
	public static int compteur;
	public int id;
	double latitude;

	double longitude;

	@Transient
	PositionGps positionGps;
	int mesureSO2;
	int mesurePM25;
	int mesurePM10;
	int mesureO3;
	int mesureNO2;
	int mesureCO;

	public StationDeMesure(double latitude, double longitude, PositionGps positionGps) {
		super();
		this.latitude = positionGps.getLatitude();
		this.longitude = positionGps.getLongitude();
		this.positionGps = positionGps;
	}

	public StationDeMesure() {
		super();
	}

	public long getIdPrincipal() {
		return idPrincipal;
	}

	public void setIdPrincipal(long idPrincipal) {
		this.idPrincipal = idPrincipal;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getMesureSO2() {
		return mesureSO2;
	}

	public void setMesureSO2(int mesureSO2) {
		this.mesureSO2 = mesureSO2;
	}

	public int getMesurePM25() {
		return mesurePM25;
	}

	public void setMesurePM25(int mesurePM25) {
		this.mesurePM25 = mesurePM25;
	}

	public int getMesurePM10() {
		return mesurePM10;
	}

	public void setMesurePM10(int mesurePM10) {
		this.mesurePM10 = mesurePM10;
	}

	public int getMesureO3() {
		return mesureO3;
	}

	public void setMesureO3(int mesureO3) {
		this.mesureO3 = mesureO3;
	}

	public int getMesureNO2() {
		return mesureNO2;
	}

	public void setMesureNO2(int mesureNO2) {
		this.mesureNO2 = mesureNO2;
	}

	public int getMesureCO() {
		return mesureCO;
	}

	public void setMesureCO(int mesureCO) {
		this.mesureCO = mesureCO;
	}

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param positionGps
	 * @param mesureSO2
	 * @param mesurePM25
	 * @param mesurePM10
	 * @param mesureO3
	 * @param mesureNO2
	 * @param mesureCO
	 */
	public StationDeMesure(PositionGps positionGps, int mesureSO2, int mesurePM25, int mesurePM10, int mesureO3,
			int mesureNO2, int mesureCO) {
		super();
		this.id = compteur;
		this.positionGps = positionGps;
		this.mesureSO2 = mesureSO2;
		this.mesurePM25 = mesurePM25;
		this.mesurePM10 = mesurePM10;
		this.mesureO3 = mesureO3;
		this.mesureNO2 = mesureNO2;
		this.mesureCO = mesureCO;
		this.latitude = positionGps.getLatitude();
		this.longitude = positionGps.getLongitude();
		compteur++;
	}

	public static int getCompteur() {
		return compteur;
	}

	public static void setCompteur(int compteur) {
		StationDeMesure.compteur = compteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PositionGps getPositionGps() {
		return positionGps;
	}

	public void setPositionGps(PositionGps positionGps) {
		this.positionGps = positionGps;
	}

}
