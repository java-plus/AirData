package fr.diginamic.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;



@Entity
public class Commune {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	String nom;
	String code;
	String codesPostaux;
	double latitude;
	double longitude;
	@Transient
	PositionGps centre;
	int population;
	
	
	@Transient
	List<StationDeMesure> listeDeStationsDeMesures;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	StationDeMesure StationDeMesure;
	int distance;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureSO2_id")
	StationDeMesure StationDeMesureSO2;
	int distanceSO2;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesurePM25_id")
	StationDeMesure StationDeMesurePM25;
	int distancePM25;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesurePM10_id")
	StationDeMesure StationDeMesurePM10;
	int distancePM10;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureO3_id")
	StationDeMesure StationDeMesureO3;
	int distanceO3;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureNO2_id")
	StationDeMesure StationDeMesureNO2;
	int distanceNO2;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureCO_id")
	StationDeMesure StationDeMesureCO;
	int distanceCO;
	
	public StationDeMesure getStationDeMesure() {
		return StationDeMesure;
	}

	public void setStationDeMesure(StationDeMesure stationDeMesure) {
		StationDeMesure = stationDeMesure;
	}

	public StationDeMesure getStationDeMesureSO2() {
		return StationDeMesureSO2;
	}

	public void setStationDeMesureSO2(StationDeMesure stationDeMesureSO2) {
		StationDeMesureSO2 = stationDeMesureSO2;
	}

	public StationDeMesure getStationDeMesurePM25() {
		return StationDeMesurePM25;
	}

	public void setStationDeMesurePM25(StationDeMesure stationDeMesurePM25) {
		StationDeMesurePM25 = stationDeMesurePM25;
	}

	public StationDeMesure getStationDeMesurePM10() {
		return StationDeMesurePM10;
	}

	public void setStationDeMesurePM10(StationDeMesure stationDeMesurePM10) {
		StationDeMesurePM10 = stationDeMesurePM10;
	}

	public StationDeMesure getStationDeMesureO3() {
		return StationDeMesureO3;
	}

	public void setStationDeMesureO3(StationDeMesure stationDeMesureO3) {
		StationDeMesureO3 = stationDeMesureO3;
	}

	public StationDeMesure getStationDeMesureNO2() {
		return StationDeMesureNO2;
	}

	public void setStationDeMesureNO2(StationDeMesure stationDeMesureNO2) {
		StationDeMesureNO2 = stationDeMesureNO2;
	}

	public StationDeMesure getStationDeMesureCO() {
		return StationDeMesureCO;
	}

	public void setStationDeMesureCO(StationDeMesure stationDeMesureCO) {
		StationDeMesureCO = stationDeMesureCO;
	}

	
	
	public Commune() {
		super();
	}
	
	public Commune(String nom, String code, String codesPostaux, PositionGps centre, int population) {
		super();
		this.nom = nom;
		this.code = code;
		this.codesPostaux = codesPostaux;
		this.centre = centre;
		this.population = population;
		
	}

	
	
	
	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param code
	 * @param codesPostaux
	 * @param centre
	 * @param population
	 */
	public Commune(String nom, String code, String codesPostaux, PositionGps centre, int population,
			int idStationDeMesure) {
		super();
		this.nom = nom;
		this.code = code;
		this.codesPostaux = codesPostaux;
		this.centre = centre;
		this.population = population;
		
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<StationDeMesure> getListeDeStationsDeMesures() {
		return listeDeStationsDeMesures;
	}

	public void setListeDeStationsDeMesures(List<StationDeMesure> listeDeStationsDeMesures) {
		this.listeDeStationsDeMesures = listeDeStationsDeMesures;
	}

	
	
	
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodesPostaux() {
		return codesPostaux;
	}

	public void setCodesPostaux(String codesPostaux) {
		this.codesPostaux = codesPostaux;
	}

	public PositionGps getCentre() {
		return centre;
	}

	public void setCentre(PositionGps centre) {
		this.centre = centre;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistanceSO2() {
		return distanceSO2;
	}

	public void setDistanceSO2(int distanceSO2) {
		this.distanceSO2 = distanceSO2;
	}

	public int getDistancePM25() {
		return distancePM25;
	}

	public void setDistancePM25(int distancePM25) {
		this.distancePM25 = distancePM25;
	}

	public int getDistancePM10() {
		return distancePM10;
	}

	public void setDistancePM10(int distancePM10) {
		this.distancePM10 = distancePM10;
	}

	public int getDistanceO3() {
		return distanceO3;
	}

	public void setDistanceO3(int distanceO3) {
		this.distanceO3 = distanceO3;
	}

	public int getDistanceNO2() {
		return distanceNO2;
	}

	public void setDistanceNO2(int distanceNO2) {
		this.distanceNO2 = distanceNO2;
	}

	public int getDistanceCO() {
		return distanceCO;
	}

	public void setDistanceCO(int distanceCO) {
		this.distanceCO = distanceCO;
	}

	
	

}
