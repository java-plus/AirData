package fr.diginamic.utils;

import java.util.List;

import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;

public abstract class MesureUtils {

	public static StationDeMesurePollution obtenirStationDeMesurePollutionCorrespondante(Double latitude,
			Double longitude, List<StationDeMesurePollution> listeDeStationsDeMesures) {

		for (StationDeMesurePollution stationDeMesure : listeDeStationsDeMesures) {
			if (stationDeMesure.getLatitude().equals(latitude) && stationDeMesure.getLongitude().equals(longitude)) {
				return stationDeMesure;

			}
		}
		return null;

	}

	public static StationDeMesureMeteo obtenirStationDeMesureMeteoCorrespondante(Double latitude, Double longitude,
			List<StationDeMesureMeteo> listeDeStationDeMesures) {


		for (StationDeMesureMeteo stationDeMesure : listeDeStationDeMesures) {
			if (stationDeMesure.getLatitude().equals(latitude) && stationDeMesure.getLongitude().equals(longitude)) {
				return stationDeMesure;

			}
		}
		return null;
	}

}
