package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * represente une station de mesure de pollution
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDeMesurePollution {

	/**
	 * id de la station en base de données
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * latitude de la station
	 */
	private Double latitude;
	/**
	 * longitude de la station
	 */
	private Double longitude;

	/**
	 * indique si la station mesure le SO2
	 */
	private Boolean mesureSO2 = false;
	/**
	 * indique si la station mesure le PM25
	 */
	private Boolean mesurePM25 = false;
	/**
	 * indique si la station mesure le PM10
	 */
	private Boolean mesurePM10 = false;
	/**
	 * indique si la station mesure l’ 03
	 */
	private Boolean mesureO3 = false;
	/**
	 * indique si la station mesure le NO2
	 */
	private Boolean mesureNO2 = false;
	/**
	 * indique si la station mesure le CO
	 */
	private Boolean mesureCO = false;

	/**
	 * Constructeur
	 * @param latitude latitude de la station
	 * @param longitude latitude de la station
	 */
	public StationDeMesurePollution(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;

	}

}
