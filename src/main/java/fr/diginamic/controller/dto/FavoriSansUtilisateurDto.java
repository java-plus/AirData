package fr.diginamic.controller.dto;

import fr.diginamic.entites.Commune;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe représentant un objet FavoriSansUtilisateurDto Cette classe a été
 * créée car un objet favori contenait une liste d'utilisateur qui eux même
 * contenaient une liste de favoris qui eux même contenaient une liste
 * d'utilisateurs... bref favoriCeption
 * 
 * @author Diginamic02
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriSansUtilisateurDto {

	/**
	 * id du favori
	 */
	private Integer id;

	/**
	 * commune : Commune représente la commune selectionnée par l'utilisateur
	 * pour caractériser son favori
	 */
	private Commune commune;

	/**
	 * weatherDescription : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean weatherDescription = false;
	/**
	 * weatherIcon : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean weatherIcon = false;
	/**
	 * temperature : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean temperature = false;
	/**
	 * pressure : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean pressure = false;
	/**
	 * humidity : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean humidity = false;
	/**
	 * tempMin : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean tempMin = false;
	/**
	 * tempMax : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean tempMax = false;
	/**
	 * windSpeed : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean windSpeed = false;
	/**
	 * windDegrees : Boolean représente si oui ou non l'utilisateur a
	 * sélectionné cette information comme apparaissant dans les résultats de
	 * son favori
	 */
	private Boolean windDegrees = false;

	/**
	 * mesureSO2 : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean mesureSO2 = false;
	/**
	 * mesurePM25 : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean mesurePM25 = false;
	/**
	 * mesurePM10 : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean mesurePM10 = false;
	/**
	 * mesureO3 : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean mesureO3 = false;
	/**
	 * mesureNO2 : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean mesureNO2 = false;
	/**
	 * mesureCO : Boolean représente si oui ou non l'utilisateur a sélectionné
	 * cette information comme apparaissant dans les résultats de son favori
	 */
	private Boolean mesureCO = false;

}
