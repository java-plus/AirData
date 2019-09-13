/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;

import fr.diginamic.entites.Commune;
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
public class AnalyseMesureDto {

	private String nom;
	private String codeCommune;

	private Integer population;

	private String indicateur;
	private Double valeur;

	private ZonedDateTime dateDebut;
	private ZonedDateTime dateFin;

	public AnalyseMesureDto(Commune commune) {
		this.population = commune.getPopulation();
		this.nom = commune.getNom();
		this.codeCommune = commune.getCodeCommune();
	}

}
