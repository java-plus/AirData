package fr.diginamic.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet Commune représentant une commune
 * 
 * @author Diginamic02
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commune implements Serializable {

	/** id : Integer . est l'identifiant en base de donnée de la commune */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	/** nom : String est le nom de la commune (exemple: Nantes) */
	@NotBlank
	private String nom;
	/**
	 * codeCommune : String est le code insee de la commune, un code insee est
	 * attribué par l'état à chaque commune et est unique à chaque commune en
	 * France (contrairement au code postal), exemple: code insee de nantes:
	 * 44109
	 */
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;
	/** latitude : Double est la latitude de la commune */
	@NotNull
	private Double latitude;
	/** longitude : Double est la longitude de la commune */
	@NotNull
	private Double longitude;
	/** population : Integer est la population de la commune */
	@NotNull
	private Integer population;

	@Transient
	List<StationDeMesurePollution> listeDeStationsDeMesures;

	/**
	 * stationDeMesureMeteo : StationDeMesureMeteo est la station de mesure
	 * Meteo la plus proche de la commune
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesureMeteo_id")
	StationDeMesureMeteo stationDeMesureMeteo;
	/**
	 * distanceStationDeMesureMeteo : int est la distance en mètre d ela station
	 * meteo la plus proche de la commune
	 */
	int distanceStationDeMesureMeteo;

	/**
	 * StationDeMesure : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesure_id")
	StationDeMesurePollution StationDeMesure;
	/**
	 * distance : int est la distance en mètres de la station de mesure
	 * pollution la plus proche de la commune
	 */
	int distance;

	/**
	 * stationDeMesureSO2 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le SO2 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesureSO2_id")
	StationDeMesurePollution stationDeMesureSO2;
	int distanceSO2;

	/**
	 * stationDeMesurePM25 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le PM2.5 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesurePM25_id")
	StationDeMesurePollution stationDeMesurePM25;
	int distancePM25;

	/**
	 * stationDeMesurePM10 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le PM10 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesurePM10_id")
	StationDeMesurePollution stationDeMesurePM10;
	int distancePM10;

	/**
	 * stationDeMesureO3 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le O3 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesureO3_id")
	StationDeMesurePollution stationDeMesureO3;
	int distanceO3;

	/**
	 * stationDeMesureNO2 : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le NO2 dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesureNO2_id")
	StationDeMesurePollution stationDeMesureNO2;
	int distanceNO2;

	/**
	 * stationDeMesureCO : StationDeMesurePollution est la station de mesure
	 * pollution la plus proche de la commune et mesurant le CO dans l'air
	 */
	@ManyToOne
	@JoinColumn(name = "StationDeMesureCO_id")
	StationDeMesurePollution stationDeMesureCO;
	int distanceCO;

	String typeGeometryGeojson;

	@Column(length = 100000)
	String coordinatesGeometryGeojson;

	@ManyToOne
	@JoinColumn(name = "MesureSO2_id")
	MesurePollution mesurePollutionSO2;

	@ManyToOne
	@JoinColumn(name = "MesurePM25_id")
	MesurePollution mesurePollutionPM25;

	@ManyToOne
	@JoinColumn(name = "MesurePM10_id")
	MesurePollution mesurePollutionPM10;

	@ManyToOne
	@JoinColumn(name = "MesureNO2_id")
	MesurePollution mesurePollutionNO2;

	@ManyToOne
	@JoinColumn(name = "MesureO3_id")
	MesurePollution mesurePollutionO3;

	@ManyToOne
	@JoinColumn(name = "MesureCO_id")
	MesurePollution mesurePollutionCO;

	/**
	 * Constructeur retournant une instance de Commune
	 * 
	 * @param nom
	 * @param codeCommune
	 * @param latitude
	 * @param longitude
	 * @param population
	 */
	public Commune(@NotBlank String nom, @NotBlank @Pattern(regexp = "^[0-9]*$") String codeCommune,
			@NotNull double latitude, @NotNull double longitude, @NotNull Integer population,
			@NotNull String typeGeometryGeojson, @NotNull String coordinatesGeometryGeojson) {
		super();
		this.nom = nom;
		this.codeCommune = codeCommune;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
		this.typeGeometryGeojson = typeGeometryGeojson;
		this.coordinatesGeometryGeojson = coordinatesGeometryGeojson;
	}

}
