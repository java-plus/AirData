package fr.diginamic.geojson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommuneGeojson {

	private String type;
	private Geometry geometry;
	private Properties properties;

}
