package fr.diginamic.entites;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe représente le compte d'un utilisateur enregistré en base de
 * donnée.
 * 
 * @author Diginamic02
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteUtilisateur {

	/**
	 * id : Integer représente l'id du compte utilisateur, celui ci est généré
	 * automatiquement par Spring lors de la création d'un compte utilisateur
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * dateInscription : ZonedDateTime représente la date auquel l'utilisateur a
	 * créé son compte
	 */
	private ZonedDateTime dateInscription;
	/**
	 * notificationMeteo : Boolean représente si l'utilisateur veut ou non
	 * recevoir les alertes (ou notifications) liées à la météo. Ces alertes
	 * sont créées par les ADMIINs
	 */
	private Boolean notificationMeteo = false;
	/**
	 * notificationPollution : Boolean représente si l'utilisateur veut ou non
	 * recevoir les alertes (ou notifications) liées à la pollution. Ces alertes
	 * sont créées par les ADMIINs
	 */
	private Boolean notificationPollution = false;

	/**
	 * Cette methode est un trigger qui rempli automatiquement l'attribut
	 * dateInscription du compte utilisateur lorsqu'il est créé. Il prend comme
	 * valeur la date du jour
	 * 
	 */
	@PrePersist
	public void prepersist() {
		this.dateInscription = ZonedDateTime.now();
	}

}
