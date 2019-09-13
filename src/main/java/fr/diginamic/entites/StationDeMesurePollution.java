package fr.diginamic.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDeMesurePollution {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double latitude;
	private Double longitude;

	private Boolean mesureSO2 = false;
	private Boolean mesurePM25 = false;
	private Boolean mesurePM10 = false;
	private Boolean mesureO3 = false;
	private Boolean mesureNO2 = false;
	private Boolean mesureCO = false;

	public StationDeMesurePollution(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;

	}

}
