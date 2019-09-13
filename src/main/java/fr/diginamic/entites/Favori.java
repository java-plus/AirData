package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favori {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	Commune commune;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UTILISATEUR_ID")
	private Utilisateur utilisateur;

	private Boolean weatherDescription = false;
	private Boolean weatherIcon = false;
	private Boolean temperature = false;
	private Boolean pressure = false;
	private Boolean humidity = false;
	private Boolean tempMin = false;
	private Boolean tempMax = false;
	private Boolean windSpeed = false;
	private Boolean windDegrees = false;

	private Boolean mesureSO2 = false;
	private Boolean mesurePM25 = false;
	private Boolean mesurePM10 = false;
	private Boolean mesureO3 = false;
	private Boolean mesureNO2 = false;
	private Boolean mesureCO = false;

	private Boolean population = false;

}
