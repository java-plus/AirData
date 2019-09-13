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
 * @author Eloi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommuneMesuresDto {

	private List<MesureMeteo> listeMeteo;

	List<MesurePollution> listePollution;

	private Integer population;

}
