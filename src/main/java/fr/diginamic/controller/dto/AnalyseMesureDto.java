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
 * Classe Dto à destination du Front-end. <br/>
 * Elle représente l'historique d'une indicateur (une MesureDto) sur une commune.
 * Concerne aussi bien l'historisation d'une mesure météo que d'une mesure pollution. <br/>
 * 
 * @see MesureDto
 * 
 * @author Eloi
 * 
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseMesureDto {

	/** nom : String Le nom de la commune */
	private String nom;

	/** population : Integer La population de la commune */
	private Integer population;

	/** donnees : List<MesureDto> Liste de MesureDto */
	private List<MesureDto> donnees;

	/** indicateur : String L'indicateur concerné */
	private String indicateur;

	/** dateDebut : ZonedDateTime La date de début de l'historique */
	private ZonedDateTime dateDebut;

	/** dateFin : ZonedDateTime La date de find e l'historique */
	private ZonedDateTime dateFin;

}
