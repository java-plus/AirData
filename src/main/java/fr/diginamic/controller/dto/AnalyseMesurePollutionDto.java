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
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseMesurePollutionDto {

	private Double valeur;
	private String typeDeDonnee;
	private ZonedDateTime date;
}
