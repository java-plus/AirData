/**
 * 
 */
package fr.diginamic.controller.dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eloi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriDtoPostAvecId {

	private Integer id;

	/**
	 * codeCommune : Commune représente la commune selectionnée par l'utilisateur
	 * pour caractériser son favori
	 */
	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;

	/**
	 * weatherDescription : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean weatherDescription;
	/**
	 * weatherIcon : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean weatherIcon;
	/**
	 * temperature : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean temperature;
	/**
	 * pressure : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean pressure;
	/**
	 * humidity : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean humidity;
	/**
	 * tempMin : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean tempMin;
	/**
	 * tempMax : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean tempMax;
	/**
	 * windSpeed : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean windSpeed;
	/**
	 * windDegrees : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean windDegrees;

	/**
	 * mesureSO2 : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean mesureSO2;
	/**
	 * mesurePM25 : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean mesurePM25;
	/**
	 * mesurePM10 : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean mesurePM10;
	/**
	 * mesureO3 : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean mesureO3;
	/**
	 * mesureNO2 : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean mesureNO2;
	/**
	 * mesureCO : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean mesureCO;

	/**
	 * population : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean population;
}
