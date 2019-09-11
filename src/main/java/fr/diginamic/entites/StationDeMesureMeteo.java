package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDeMesureMeteo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private Double latitude;
	@NotNull
	private Double longitude;

	/**
	 * Constructeur
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public StationDeMesureMeteo(@NotNull Double latitude, @NotNull Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
