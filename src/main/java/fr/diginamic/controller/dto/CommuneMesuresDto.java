/**
 * 
 */
package fr.diginamic.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Eloi
 *
 */
public class CommuneMesuresDto {

	@NotBlank
	private String nom;
	@NotBlank
	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;

	// mesures météo :
	@NotBlank
	private String weatherDescription;
	@NotBlank
	private String weatherIcon;
	@NotNull
	private Long temperature;
	@NotNull
	private Long pressure;
	@NotNull
	private Integer humidity;
	@NotNull
	private Long tempMin;
	@NotNull
	private Long tempMax;
	@NotNull
	private Long windSpeed;
	@NotNull
	private Integer windDegrees;

	// mesures pollution :

	// mesure localisation
	@NotNull
	private Integer population;

}
