/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Classe représentant la requète GET pour récuperer l'historique d'un indicateur sur une commune, sur une période
 * 
 * 
 * @author Eloi
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseMesureDtoPost {

	/** codeCommune : String Le code de la commune (équivalent code insee) concernée par la recherche */
	private String codeCommune;

	/** indicateur : String L'indicateur météo ou pollution désiré */
	private String indicateur;

	/** dateDebut : ZonedDateTime La date de début de la période concernée par la recherche */
	private ZonedDateTime dateDebut;

	/** dateFin : ZonedDateTime La date de fin de la période concernée par la recherche */
	private ZonedDateTime dateFin;

}
