/**
 * 
 */
package fr.diginamic.controller.dto;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eloi
 *
 */
@Data
@NoArgsConstructor
public class IndicateurAvecDateEtValeurDto {

	private ZonedDateTime date;
	private Double valeur;

}
