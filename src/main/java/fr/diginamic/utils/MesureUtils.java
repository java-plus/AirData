package fr.diginamic.utils;

import java.util.List;

import fr.diginamic.entites.StationDeMesurePollution;

public abstract class MesureUtils {

	public static StationDeMesurePollution obtenirStationDeMesureCorrespondante(double latitude, double longitude,
			List<StationDeMesurePollution> listeDeStationsDeMesures) {

		for (StationDeMesurePollution stationDeMesure : listeDeStationsDeMesures) {
			if (stationDeMesure.getLatitude() == latitude && stationDeMesure.getLongitude() == longitude) {
				return stationDeMesure;

			}
		}
		return null;

	}

}
