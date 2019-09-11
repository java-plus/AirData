package fr.diginamic.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@NotBlank
	private String nom;
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;
	@NotNull
	private double latitude;

	@NotNull
	private double longitude;
	@NotNull
	private Integer population;

	@Transient
	List<StationDeMesurePollution> listeDeStationsDeMesures;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureMeteo_id")
	StationDeMesureMeteo StationDeMesureMeteo;
	int distanceStationDeMesureMeteo;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	StationDeMesurePollution StationDeMesure;
	int distance;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureSO2_id")
	StationDeMesurePollution StationDeMesureSO2;
	int distanceSO2;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesurePM25_id")
	StationDeMesurePollution StationDeMesurePM25;
	int distancePM25;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesurePM10_id")
	StationDeMesurePollution StationDeMesurePM10;
	int distancePM10;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureO3_id")
	StationDeMesurePollution StationDeMesureO3;
	int distanceO3;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureNO2_id")
	StationDeMesurePollution StationDeMesureNO2;
	int distanceNO2;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesureCO_id")
	StationDeMesurePollution StationDeMesureCO;
	int distanceCO;

	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param codeCommune
	 * @param latitude
	 * @param longitude
	 * @param population
	 */
	public Commune(@NotBlank String nom, @NotBlank @Pattern(regexp = "^[0-9]*$") String codeCommune,
			@NotNull double latitude, @NotNull double longitude, @NotNull Integer population) {
		super();
		this.nom = nom;
		this.codeCommune = codeCommune;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
	}

}
