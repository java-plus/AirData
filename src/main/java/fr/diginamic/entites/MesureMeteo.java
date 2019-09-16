package fr.diginamic.entites;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente une série de donnée météo enregistrée en base de ddonnée
 * concernant une certaine date et une certaine station Meteo
 * 
 * @author Diginamic02
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesureMeteo {

	/**
	 * id : Long représente l'id d'une mesure météo. Cette id est sorti de l'API
	 * qui nous fourni les informations
	 */
	@Id
	private Long id;
	/** date : ZonedDateTime représente la date de la mesure météo */
	@NotNull
	private ZonedDateTime date;
	/**
	 * stationDeMesure : StationDeMesureMeteo représente la station météo qui a
	 * enregistré la mesure météo. Dans la table Mesure_Meteo, cette station
	 * météo est représentée par son id mais Spring ressort l'objet StationMeteo
	 * avec toutes les informations qui la compose
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	private StationDeMesureMeteo stationDeMesure;
	/**
	 * weatherDescription : String représente la description de la météo, cette
	 * valeur est sorti de l'API qui nous fourni les informations et est en
	 * anglais
	 */
	@NotBlank
	private String weatherDescription;
	/**
	 * weatherIcon : String représente la description de la météo, cette valeur
	 * est sorti de l'API qui nous fourni les informations
	 */
	@NotBlank
	private String weatherIcon;
	/**
	 * temperature : Double représente la temérature enregistrée par la station
	 * météo concernée, cette valeur est sorti de l'API qui nous fourni les
	 * informations
	 */
	@NotNull
	private Double temperature;
	/**
	 * pressure : Double représente la pression atmosphérique enregistrée par la
	 * station météo concernée, cette valeur est sorti de l'API qui nous fourni
	 * les informations
	 */
	@NotNull
	private Double pressure;
	/**
	 * humidity : Integer représente l'humidité enregistrée par la station météo
	 * concernée, cette valeur est sorti de l'API qui nous fourni les
	 * informations
	 */

	@NotNull
	private Integer humidity;
	/**
	 * tempMin : Double représente la temérature minimum enregistrée par la
	 * station météo concernée, cette valeur est sorti de l'API qui nous fourni
	 * les informations
	 */
	@NotNull
	private Double tempMin;
	/**
	 * tempMax : Double représente la temérature maximale enregistrée par la
	 * station météo concernée, cette valeur est sorti de l'API qui nous fourni
	 * les informations
	 */
	@NotNull
	private Double tempMax;
	/**
	 * windSpeed : Double représente la vitesse du vent enregistrée par la
	 * station météo concernée, cette valeur est sorti de l'API qui nous fourni
	 * les informations
	 */
	@NotNull
	private Double windSpeed;
	@NotNull
	private Integer windDegrees;

}
