/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eloi
 * @param <T>
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseMesureDto {

	private String nom;

	private Integer population;

	private List<MesureDto> donnees;

	private String indicateur;

	private ZonedDateTime dateDebut;
	private ZonedDateTime dateFin;

}
