/**
 * 
 */
package fr.diginamic.controller.dto;

import java.util.List;

import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Classe Dto à destination du front-end représentant l'ensemble de toutes les mesures météo et pollution sur une commune
 * 
 * @see MesureMeteo
 * @see MesurePollution
 * 
 * @author Eloi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommuneMesuresDto {

	/** listeMeteo : List<MesureMeteo> Liste de mesures météo */
	private List<MesureMeteo> listeMeteo;

	/** listePollution : List<MesurePollution> liste de mesures pollution */
	List<MesurePollution> listePollution;

	/** population : Integer Popution de la commune */
	private Integer population;

}
