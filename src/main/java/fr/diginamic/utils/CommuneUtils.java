package fr.diginamic.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;

/**
 * Cette classe permet de manipuler et de créer les objets Communes
 * 
 * @author Diginamic02
 *
 */
public class CommuneUtils {

	/**
	 * Cette methode permet de lier les communes à leur station de mesure
	 * pollution les plus proche. Cette methode calcule pour chaque polluant la
	 * station de mesure la plus proche disposant de mesures liées à ce polluant
	 * 
	 * @param listeDesCommunes
	 * @param listeDeStationsDeMesure
	 * @return
	 */
	public static List<Commune> obtenirLesStationsDeMesuresLesPlusProches(List<Commune> listeDesCommunes,
			List<StationDeMesurePollution> listeDeStationsDeMesure) {

		for (Commune commune : listeDesCommunes) {
			int idStationDeMesureLaPlusProche;
			double distanceStationDeMesureLaPlusProche = Double.MAX_VALUE;
			double distanceStationDeMesureLaPlusProcheSO2 = Double.MAX_VALUE;
			double distanceStationDeMesureLaPlusProchePM25 = Double.MAX_VALUE;
			double distanceStationDeMesureLaPlusProchePM10 = Double.MAX_VALUE;
			double distanceStationDeMesureLaPlusProcheO3 = Double.MAX_VALUE;
			double distanceStationDeMesureLaPlusProcheNO2 = Double.MAX_VALUE;
			double distanceStationDeMesureLaPlusProcheCO = Double.MAX_VALUE;

			for (StationDeMesurePollution stationDeMesure : listeDeStationsDeMesure) {
				double distancecalculee;
				distancecalculee = Math.sqrt(Math.pow(stationDeMesure.getLatitude() - commune.getLatitude(), 2)
						+ Math.pow(stationDeMesure.getLongitude() - commune.getLongitude(), 2));
				if (distancecalculee < distanceStationDeMesureLaPlusProche) {
					distanceStationDeMesureLaPlusProche = distancecalculee;
					commune.setDistance((int) (distancecalculee * 111110));
					commune.setStationDeMesure(stationDeMesure);
				}
				if (distancecalculee < distanceStationDeMesureLaPlusProcheSO2
						&& stationDeMesure.getMesureSO2().equals(true)) {
					distanceStationDeMesureLaPlusProcheSO2 = distancecalculee;
					commune.setDistanceSO2((int) (distancecalculee * 111110));
					commune.setStationDeMesureSO2(stationDeMesure);
				}
				if (distancecalculee < distanceStationDeMesureLaPlusProchePM25
						&& stationDeMesure.getMesurePM25().equals(true)) {
					distanceStationDeMesureLaPlusProchePM25 = distancecalculee;
					commune.setDistancePM25((int) (distancecalculee * 111110));
					commune.setStationDeMesurePM25(stationDeMesure);
				}
				if (distancecalculee < distanceStationDeMesureLaPlusProchePM10
						&& stationDeMesure.getMesurePM10().equals(true)) {
					distanceStationDeMesureLaPlusProchePM10 = distancecalculee;
					commune.setDistancePM10((int) (distancecalculee * 111110));
					commune.setStationDeMesurePM10(stationDeMesure);
				}
				if (distancecalculee < distanceStationDeMesureLaPlusProcheO3
						&& stationDeMesure.getMesureO3().equals(true)) {
					distanceStationDeMesureLaPlusProcheO3 = distancecalculee;
					commune.setDistanceO3((int) (distancecalculee * 111110));
					commune.setStationDeMesureO3(stationDeMesure);
				}
				if (distancecalculee < distanceStationDeMesureLaPlusProcheNO2
						&& stationDeMesure.getMesureNO2().equals(true)) {
					distanceStationDeMesureLaPlusProcheNO2 = distancecalculee;
					commune.setDistanceNO2((int) (distancecalculee * 111110));
					commune.setStationDeMesureNO2(stationDeMesure);
				}
				if (distancecalculee < distanceStationDeMesureLaPlusProcheCO
						&& stationDeMesure.getMesureCO().equals(true)) {
					distanceStationDeMesureLaPlusProcheCO = distancecalculee;
					commune.setDistanceCO((int) (distancecalculee * 111110));
					commune.setStationDeMesureCO(stationDeMesure);
				}
			}

		}

		return listeDesCommunes;
	}

	/**
	 * Cette methode permet de lier les communes à leur station de mesure meteo
	 * les plus proche.
	 * 
	 * @param listeDesCommunes
	 * @param listeDeStationsDeMesureMeteo
	 * @return
	 */
	public static List<Commune> obtenirLesStationsDeMesuresMeteoLesPlusProches(List<Commune> listeDesCommunes,
			List<StationDeMesureMeteo> listeDeStationsDeMesureMeteo) {

		for (Commune commune : listeDesCommunes) {
			double distanceStationDeMesureMeteoLaPlusProche = Double.MAX_VALUE;

			for (StationDeMesureMeteo stationDeMesureMeteo : listeDeStationsDeMesureMeteo) {
				double distancecalculee;
				distancecalculee = Math.sqrt(Math.pow(stationDeMesureMeteo.getLatitude() - commune.getLatitude(), 2)
						+ Math.pow(stationDeMesureMeteo.getLongitude() - commune.getLongitude(), 2));

				if (distancecalculee < distanceStationDeMesureMeteoLaPlusProche) {
					distanceStationDeMesureMeteoLaPlusProche = distancecalculee;
					commune.setDistanceStationDeMesureMeteo((int) (distancecalculee * 111110));
					commune.setStationDeMesureMeteo(stationDeMesureMeteo);
				}
			}

		}

		return listeDesCommunes;
	}

	public static List<Commune> obtenirMesuresPollutionLesPlusRecentes(List<Commune> listeDesCommunes,
			List<MesurePollution> listeDesMesuresPollution) {
		for (Commune commune : listeDesCommunes) {

			String[] typeDeDonnee = { "CO", "NO2", "PM10", "O3", "PM2.5", "SO2" };
			StationDeMesurePollution[] stationDeMesurePollutionConcerne = { commune.getStationDeMesureCO(),
					commune.getStationDeMesureNO2(), commune.getStationDeMesurePM10(), commune.getStationDeMesureO3(),
					commune.getStationDeMesurePM25(), commune.getStationDeMesureSO2() };

			for (int i = 0; i < typeDeDonnee.length; i++) {

				Instant minInstant = Instant.ofEpochMilli(Long.MIN_VALUE);
				ZonedDateTime dateMesurePollutionLaPlusRecente = minInstant.atZone(ZoneOffset.UTC);
				for (MesurePollution mesurePollution : listeDesMesuresPollution) {
					boolean mesureTrouvee = false;
					if (mesurePollution.getTypeDeDonnee().equals(typeDeDonnee[i])
							&& mesurePollution.getStationDeMesure().equals(stationDeMesurePollutionConcerne[i])) {
						if (dateMesurePollutionLaPlusRecente.compareTo(mesurePollution.getDate()) < 0) {
							dateMesurePollutionLaPlusRecente = mesurePollution.getDate();
							mesureTrouvee = true;
						}
					}

					if (mesureTrouvee) {
						if (typeDeDonnee[i].equals("CO")) {

							commune.setMesurePollutionCO(mesurePollution);
						}
						if (typeDeDonnee[i].equals("NO2")) {
							commune.setMesurePollutionNO2(mesurePollution);
						}
						if (typeDeDonnee[i].equals("PM10")) {
							commune.setMesurePollutionPM10(mesurePollution);
						}
						if (typeDeDonnee[i].equals("O3")) {
							commune.setMesurePollutionO3(mesurePollution);
						}
						if (typeDeDonnee[i].equals("PM2.5")) {
							commune.setMesurePollutionPM25(mesurePollution);
						}
						if (typeDeDonnee[i].equals("SO2")) {
							commune.setMesurePollutionSO2(mesurePollution);
						}

					}
				}

			}

		}

		return listeDesCommunes;
	}

}
