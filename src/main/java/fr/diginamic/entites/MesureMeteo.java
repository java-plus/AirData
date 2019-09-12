package fr.diginamic.entites;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
	private Long id;
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

}
