package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


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
	@NotBlank
	private double latitude;
	@NotBlank
	private double longitude;
	@Transient
	private PositionGps centre;
	@NotBlank
	private Integer population;
	
	
	@Transient
	List<StationDeMesurePollution> listeDeStationsDeMesures;
	
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
	
	public Commune(String nom, String code, PositionGps centre, int population) {
		super();
		this.nom = nom;
		this.codeCommune = code;
		this.centre = centre;
		this.population = population;
		
	}

	/**
	 * Constructeur
	 * 
	 * @param nom
	 * @param code
	 * @param codesPostaux
	 * @param centre
	 * @param population
	 */
	public Commune(String nom, String code, PositionGps centre, int population,
			int idStationDeMesure) {
		super();
		this.nom = nom;
		this.codeCommune = code;
		this.centre = centre;
		this.population = population;
		
	}
}
