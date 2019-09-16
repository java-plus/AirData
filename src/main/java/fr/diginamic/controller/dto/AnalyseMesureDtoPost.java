/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eloi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseMesureDtoPost {

	private String codeCommune;

	private String indicateur;

	private ZonedDateTime dateDebut;
	private ZonedDateTime dateFin;

}
