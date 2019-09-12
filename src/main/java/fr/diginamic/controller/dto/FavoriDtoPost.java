
package fr.diginamic.controller.dto;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriDtoPost {

	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;

	private Boolean weatherDescription;
	private Boolean weatherIcon;
	private Boolean temperature;
	private Boolean pressure;
	private Boolean humidity;
	private Boolean tempMin;
	private Boolean tempMax;
	private Boolean windSpeed;
	private Boolean windDegrees;

	private Boolean mesureSO2;
	private Boolean mesurePM25;
	private Boolean mesurePM10;
	private Boolean mesureO3;
	private Boolean mesureNO2;
	private Boolean mesureCO;

	private Boolean population;

}
