package fr.diginamic.utils;

import java.util.List;

import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;

/**
 * Cette classe permet de connaitre la station de mesure la plus proche par
 * rapport à une position géographique (latitude + longitude)
 * 
 * @author Diginamic02
 *
 */
public abstract class MesureUtils {

	/**
	 * Cette methode permet de connaitre la station de mesure pollution la plus
	 * proche par rapport à une position géographique (latitude + longitude)
	 * 
	 * @param latitude
	 * @param longitude
	 * @param listeDeStationsDeMesures
	 * @return
	 */
	public static StationDeMesurePollution obtenirStationDeMesurePollutionCorrespondante(Double latitude,
			Double longitude, List<StationDeMesurePollution> listeDeStationsDeMesures) {

		for (StationDeMesurePollution stationDeMesure : listeDeStationsDeMesures) {
			if (stationDeMesure.getLatitude().equals(latitude) && stationDeMesure.getLongitude().equals(longitude)) {
				return stationDeMesure;

			}
		}
		return null;

	}

	/**
	 * Cette methode permet de connaitre la station de mesure meteo la plus
	 * proche par rapport à une position géographique (latitude + longitude)
	 * 
	 * @param latitude
	 * @param longitude
	 * @param listeDeStationDeMesures
	 * @return
	 */
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
