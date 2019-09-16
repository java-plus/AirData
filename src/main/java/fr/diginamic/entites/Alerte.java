package fr.diginamic.entites;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente une alerte Une alerte est créée par un admin pour
 * envoyer une notification à certains utilisateurs en fonction du taux d'un
 * indicateur. Exemple: l'admin peut créer une alerte qui enverra une
 * notification à tous les utilisateurs présents dans une commune (ou un
 * département) si le taux de NO2 dépasse un certain taux
 * 
 * @author Diginamic02
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerte {

	/**
	 * id : Integer représente l'id de l'alerte. Cette valeur est créée par
	 * Spring au moment de l'insertion en base de donnée
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * region : String représente la région sélectionné lors de la création de
	 * l'alerte
	 */
	private String region;
	/**
	 * departement : String représente le département sélectionné lors de la
	 * création de l'alerte
	 */
	private String departement;

	/**
	 * codeCommune : String représente la commune sélectionné lors de la
	 * création de l'alerte
	 */
	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;
	/**
	 * type : Type représente le type sélectionné lors de la création de
	 * l'alerte, ce type est une énumération équivalente à METEO ou POLUUTION
	 */
	@Enumerated(EnumType.STRING)
	@NotNull
	private Type type;
	/**
	 * message : String représente le message qui sera affiché à l'utilisateur
	 * recevant cette alerte
	 */
	private String message;
	/**
	 * dateDebut : ZonedDateTime représente la date de début sélectionné lors de
	 * la création de l'alerte, à partir de cette date l'alerte peut être
	 * envoyée à l'utilisateur
	 */
	@NotNull
	private ZonedDateTime dateDebut;
	/**
	 * dateFin : ZonedDateTime représente la date de fin sélectionné lors de la
	 * création de l'alerte, à partir de cette date l'alerte ne sera plus
	 * envoyée à l'utilisateur
	 */
	@NotNull
	private ZonedDateTime dateFin;

}
