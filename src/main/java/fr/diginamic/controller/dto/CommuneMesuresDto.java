/**
 * 
 */
package fr.diginamic.controller.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.diginamic.entites.Commune;
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
	private Double temperature;
	@NotNull
	private Double pressure;
	@NotNull
	private Integer humidity;
	@NotNull
	private Double tempMin;
	@NotNull
	private Double tempMax;
	@NotNull
	private Double windSpeed;
	@NotNull
	private Integer windDegrees;

	// mesures pollution :
	@NotNull
	private Double so2;
	@NotNull
	private Double pm25;
	@NotNull
	private Double pm10;
	@NotNull
	private Double o3;
	@NotNull
	private Double no2;
	@NotNull
	private Double co;
	// mesure localisation
	@NotNull
	private Integer population;

	public CommuneMesuresDto(Commune commune, List<MesureMeteo> listeMeteo, List<MesurePollution> listePollution) {

		this.nom = commune.getNom();
		this.codeCommune = commune.getCodeCommune();
		this.weatherDescription = listeMeteo.get(0).getWeatherDescription();
		this.weatherIcon = listeMeteo.get(0).getWeatherIcon();
		this.temperature = listeMeteo.get(0).getTemperature();
		this.pressure = listeMeteo.get(0).getPressure();
		this.humidity = listeMeteo.get(0).getHumidity();
		this.tempMin = listeMeteo.get(0).getTempMin();
		this.tempMax = listeMeteo.get(0).getTempMax();
		this.windSpeed = listeMeteo.get(0).getWindSpeed();
		this.windDegrees = listeMeteo.get(0).getWindDegrees();
		this.so2 = listePollution.get(0).getValeur();
		this.pm25 = listePollution.get(1).getValeur();
		this.pm10 = listePollution.get(2).getValeur();
		this.o3 = listePollution.get(3).getValeur();
		this.no2 = listePollution.get(4).getValeur();
		this.co = listePollution.get(5).getValeur();
		this.population = commune.getPopulation();

	}

}
