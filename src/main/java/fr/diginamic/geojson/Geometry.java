package fr.diginamic.geojson;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geometry {

	private String type;
	// private double[] coordinatess;
	private List<List<CoordonneeGps>> coordinates;

}
