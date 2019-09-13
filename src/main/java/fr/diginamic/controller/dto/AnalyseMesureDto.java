/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;
import java.util.List;

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
	private List<IndicateurAvecDateEtValeurDto> donnees;

	private ZonedDateTime dateDebut;
	private ZonedDateTime dateFin;

	public AnalyseMesureDto(Commune commune, List<IndicateurAvecDateEtValeurDto> listeIndic) {
		this.population = commune.getPopulation();
		this.nom = commune.getNom();
		this.codeCommune = commune.getCodeCommune();
		this.donnees = listeIndic;
	}

}
