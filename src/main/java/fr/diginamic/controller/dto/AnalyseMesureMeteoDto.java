/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;
import java.util.List;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesureMeteo;
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
public class AnalyseMesureMeteoDto {

	private String nom;
	private String codeCommune;

	private Integer population;

	private String indicateur;
	private List<MesureMeteo> donnees;

	private ZonedDateTime dateDebut;
	private ZonedDateTime dateFin;

	public AnalyseMesureMeteoDto(Commune commune, List<MesureMeteo> donnees) {
		this.population = commune.getPopulation();
		this.nom = commune.getNom();
		this.codeCommune = commune.getCodeCommune();
		this.donnees = donnees;
	}

}
