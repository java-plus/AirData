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
public class MesurePollution {

	/**
	 * id de la mesure de pollution dans la base de données
	 */
	@Id
	private String id;
	/**
	 * valeur de la mesure de pollution
	 */
	@NotNull
	private Double valeur;
	/**
	 * nom du polluant
	 */
	@NotBlank
	private String typeDeDonnee;
	/**
	 * date de la mesure
	 */
	@NotNull
	private ZonedDateTime date;
	/**
	 * la station de mesure
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	private StationDeMesurePollution stationDeMesure;

	/**
	 * Constructeur d’une mesure de pollution
	 * @param id id de la mesure de pollution dans la base de données
	 * @param valeur valeur de la mesure de pollution
	 * @param typeDeDonnee nom du polluant
	 * @param date date de la mesure
	 * @param stationDeMesure la station de mesure
	 */
	public MesurePollution(String id, double valeur, String typeDeDonnee, ZonedDateTime date,
			StationDeMesurePollution stationDeMesure) {
		super();
		this.id = id;
		this.valeur = valeur;
		this.typeDeDonnee = typeDeDonnee;
		this.date = date;

		this.stationDeMesure = stationDeMesure;
	}

}
