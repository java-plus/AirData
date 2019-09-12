package fr.diginamic.entites;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesureMeteo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private ZonedDateTime date;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	private StationDeMesureMeteo stationDeMesure;
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

	/**
	 * Constructeur
	 * 
	 * @param date
	 * @param stationDeMesure
	 * @param weatherDescription
	 * @param weatherIcon
	 * @param temperature
	 * @param pressure
	 * @param humidity
	 * @param tempMin
	 * @param tempMax
	 * @param windSpeed
	 * @param windDegrees
	 */
	public MesureMeteo(@NotNull ZonedDateTime date, StationDeMesureMeteo stationDeMesure,
			@NotBlank String weatherDescription, @NotBlank String weatherIcon, @NotNull Double temperature,
			@NotNull Double pressure, @NotNull Integer humidity, @NotNull Double tempMin, @NotNull Double tempMax,
			@NotNull Double windSpeed, @NotNull Integer windDegrees) {
		super();
		this.date = date;
		this.stationDeMesure = stationDeMesure;
		this.weatherDescription = weatherDescription;
		this.weatherIcon = weatherIcon;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.windSpeed = windSpeed;
		this.windDegrees = windDegrees;
	}

}
