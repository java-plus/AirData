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
public class MesureDto {
	private ZonedDateTime date;
	private Double valeur;
}
