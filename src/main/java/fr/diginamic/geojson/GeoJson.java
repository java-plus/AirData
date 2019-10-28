package fr.diginamic.geojson;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoJson {

	private String type;
	private List<CommuneGeojson> listeDeCommunesGeojson;

}
