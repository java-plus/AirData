/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;

import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Classe Dto représentant une Mesure de façon générique, utilisée pour envoyer aussi bien les mesures metéo et pollution sans les informations
 * inutile au front.
 * 
 * @see MesurePollution
 * @see MesureMeteo
 * @see AnalyseMesureDto
 * 
 * @author Eloi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesureDto {
	/** date : ZonedDateTime date du relevé de la mesure */
	private ZonedDateTime date;
	/** valeur : Double valeur de la mesure relevée */
	private Double valeur;

}
