package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente un favori créé par l'utilisateur
 * 
 * @author Diginamic02
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favori {

	/**
	 * id : Integer représente l'identifiant du favori en BDD. Celui-ci est
	 * généré automatiquement par Spring lors de l'insertion d'un favori en BDD
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Commune commune;

	/**
	 * utilisateur : Utilisateur représente l'utilisateur ayant créé le favori.
	 * Dans la base de donnée il est indiqué par l'id de l'utilisateur mais
	 * Spring retourne automatiquement l'objet utilisateur concerné avec toutes
	 * les informations qui le composent.
	 */
	@ManyToOne
	@JoinColumn(name = "UTILISATEUR_ID")
	private Utilisateur utilisateur;

	/**
	 * weatherDescription : Boolean représente si oui ou non l'utilisateur veut
	 * avoir accès grâce à son favori à cette information
	 */
	private Boolean weatherDescription = false;
	/**
	 * weatherIcon : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean weatherIcon = false;
	/**
	 * temperature : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean temperature = false;
	/**
	 * pressure : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean pressure = false;
	/**
	 * humidity : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean humidity = false;
	/**
	 * tempMin : Boolean représente si oui ou non l'utilisateur veut avoir accès
	 * grâce à son favori à cette information
	 */
	private Boolean tempMin = false;
	/**
	 * tempMax : Boolean représente si oui ou non l'utilisateur veut avoir accès
	 * grâce à son favori à cette information
	 */
	private Boolean tempMax = false;
	/**
	 * windSpeed : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean windSpeed = false;
	/**
	 * windDegrees : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean windDegrees = false;
	/**
	 * mesureSO2 : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean mesureSO2 = false;
	/**
	 * mesurePM25 : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean mesurePM25 = false;
	/**
	 * mesurePM10 : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean mesurePM10 = false;
	/**
	 * mesureO3 : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean mesureO3 = false;
	/**
	 * mesureNO2 : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean mesureNO2 = false;
	/**
	 * mesureCO : Boolean représente si oui ou non l'utilisateur veut avoir
	 * accès grâce à son favori à cette information
	 */
	private Boolean mesureCO = false;

}
