package fr.diginamic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente le corps de la requete http en POST effectuée sur l'url /auth
 * 
 * @author Diginamic02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfosAuthentificationPost {

	/**
	 * identifiant : String représente l'identifiant (login) renseigné par
	 * l'utilisateur
	 */
	private String identifiant;
	/** mdp : String représente le mot de passe renseigné par l'utilisateur */
	private String mdp;
}
