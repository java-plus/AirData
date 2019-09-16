package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * represente une station de mesure meteo
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDeMesureMeteo {

	/**
	 * id de la station en base de données
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * latitude de la station
	 */
	@NotNull
	private Double latitude;
	/**
	 * longitude de la station
	 */
	@NotNull
	private Double longitude;

	/**
	 * Constructeur
	 *
	 * @param latitude latitude de la station
	 * @param longitude longitude de la station
	 */
	public StationDeMesureMeteo(@NotNull Double latitude, @NotNull Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
