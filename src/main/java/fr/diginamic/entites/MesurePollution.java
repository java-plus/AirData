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
public class MesurePollution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private Double valeur;
	@NotBlank
	private String typeDeDonnee;
	@NotNull
	private ZonedDateTime date;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "StationDeMesure_id")
	private StationDeMesurePollution stationDeMesure;

	public MesurePollution(double valeur, String typeDeDonnee, ZonedDateTime date,
			StationDeMesurePollution stationDeMesure) {
		super();
		this.valeur = valeur;
		this.typeDeDonnee = typeDeDonnee;
		this.date = date;

		this.stationDeMesure = stationDeMesure;
	}

}
