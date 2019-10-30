package fr.diginamic.utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;

/**
 * Cette classe permet de manipuler les objets JSON retournés par les appels
 * APIs utilisés dans l'application
 * 
 * @author Diginamic02
 *
 */
public class JsonManipulation {

	public static JSONObject obtenirGeoJson2(List<Commune> listeDesCommunes) throws JSONException {

		JSONObject featureCollection = new JSONObject();

		JSONArray features = new JSONArray();

		for (Commune commune : listeDesCommunes) {
			JSONObject feature = new JSONObject();
			JSONObject properties = new JSONObject();

			JSONObject geometry = new JSONObject();
			geometry.put("type", commune.getTypeGeometryGeojson());
			geometry.put("coordinates", new JSONArray(commune.getCoordinatesGeometryGeojson()));

			properties.put("code", commune.getCodeCommune());
			properties.put("nom", commune.getNom());
			properties.put("pm10", commune.getMesurePollutionPM10().getValeur());
			properties.put("pm25", commune.getMesurePollutionPM25().getValeur());
			properties.put("no2", commune.getMesurePollutionNO2().getValeur());
			properties.put("so2", commune.getMesurePollutionSO2().getValeur());
			properties.put("co", commune.getMesurePollutionCO().getValeur());
			properties.put("o3", commune.getMesurePollutionO3().getValeur());
			feature.put("properties", properties);
			feature.put("geometry", geometry);
			feature.put("type", "Feature");
			features.put(feature);
		}

		featureCollection.put("features", features);
		featureCollection.put("type", "FeatureCollection");
		return featureCollection;

	}

	/**
	 * Cette methode permet de créer les objets communes à partir de la réponse
	 * des appels APIs utilisés par l'application
	 * 
	 * @param myResponse
	 * @return
	 * @throws JSONException
	 */
	public static List<Commune> obtenirLesCommunes(JSONObject myResponseCommunes) throws JSONException {

		List<Commune> listeDesCommunes = new ArrayList<Commune>();
		JSONArray tableauDesCommunes = myResponseCommunes.getJSONArray("communes");

		int countTableauDesCommunes = tableauDesCommunes.length();
		for (int i = 0; i < countTableauDesCommunes; i++) { // iterate through
															// jsonArray

			Commune communeTableau = new Commune(tableauDesCommunes.getJSONObject(i).getString("nom"),
					tableauDesCommunes.getJSONObject(i).getString("code"),
					tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
							.getDouble(1),
					tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
							.getDouble(0),
					tableauDesCommunes.getJSONObject(i).getInt("population"),
					tableauDesCommunes.getJSONObject(i).getJSONObject("contour").getString("type"), tableauDesCommunes
							.getJSONObject(i).getJSONObject("contour").getJSONArray("coordinates").toString());

			listeDesCommunes.add(communeTableau);
		}

		return listeDesCommunes;

	}

	/**
	 * Cette methode permet de créer les objets StationDeMesurePollution à
	 * partir de la réponse des appels APIs utilisés par l'application
	 * 
	 * @param myResponse
	 * @return
	 * @throws JSONException
	 */
	public static List<StationDeMesurePollution> obtenirLesStationDeMesures(JSONObject myResponse)
			throws JSONException {

		List<StationDeMesurePollution> listeDeStationsDeMesurePollution = new ArrayList<StationDeMesurePollution>();

		int count = myResponse.getJSONArray("records").length();

		for (int i = 0; i < count; i++) { // iterate through jsonArray

			JSONObject jsonObject = myResponse.getJSONArray("records").getJSONObject(i); // get
																							// jsonObject
																							// @
																							// i
																							// position

			JSONObject jsonObjectGeometry = jsonObject.getJSONObject("geometry");
			Double latitude = (Double) jsonObjectGeometry.getJSONArray("coordinates").get(1);
			Double longitude = (Double) jsonObjectGeometry.getJSONArray("coordinates").get(0);

			JSONObject jsonObjectFields = jsonObject.getJSONObject("fields");
			String typeDonnee = jsonObjectFields.getString("measurements_parameter");

			boolean stationsDeMesurePollutionExisteDeja = false;

			for (StationDeMesurePollution stationsDeMesurePollution : listeDeStationsDeMesurePollution) {
				if (stationsDeMesurePollution.getLatitude().equals(latitude)
						&& stationsDeMesurePollution.getLongitude().equals(longitude)) {
					stationsDeMesurePollutionExisteDeja = true;

				}
			}
			if (stationsDeMesurePollutionExisteDeja == false) {

				listeDeStationsDeMesurePollution.add(new StationDeMesurePollution(latitude, longitude));
			}

			for (StationDeMesurePollution stationDeMesure : listeDeStationsDeMesurePollution) {
				if (stationDeMesure.getLatitude().equals(latitude)
						&& stationDeMesure.getLongitude().equals(longitude)) {
					if (typeDonnee.equals("SO2")) {
						stationDeMesure.setMesureSO2(true);
					}
					if (typeDonnee.equals("PM2.5")) {
						stationDeMesure.setMesurePM25(true);
					}
					if (typeDonnee.equals("PM10")) {
						stationDeMesure.setMesurePM10(true);
					}
					if (typeDonnee.equals("O3")) {
						stationDeMesure.setMesureO3(true);
					}
					if (typeDonnee.equals("NO2")) {
						stationDeMesure.setMesureNO2(true);
					}
					if (typeDonnee.equals("CO")) {
						stationDeMesure.setMesureCO(true);
					}
				}

			}

		}
		return listeDeStationsDeMesurePollution;
	}

	/**
	 * Cette methode permet de créer les objets MesurePollution à partir de la
	 * réponse des appels APIs utilisés par l'application
	 * 
	 * @param myResponse
	 * @param listeDeStationDeMesure
	 * @return
	 * @throws JSONException
	 */
	public static List<MesurePollution> obtenirLesMesures(JSONObject myResponse,
			List<StationDeMesurePollution> listeDeStationDeMesure) throws JSONException {

		List<MesurePollution> listeDesMesures = new ArrayList<MesurePollution>();

		int count = myResponse.getJSONArray("records").length();

		for (int i = 0; i < count; i++) { // iterate through jsonArray

			JSONObject jsonObject = myResponse.getJSONArray("records").getJSONObject(i); // get
																							// jsonObject
																							// @
																							// i
																							// position
			String id = jsonObject.getString("recordid");
			JSONObject jsonObjectFields = jsonObject.getJSONObject("fields");
			String typeDonnee = jsonObjectFields.getString("measurements_parameter");
			double valeur = jsonObjectFields.getDouble("measurements_value");
			String date = jsonObjectFields.getString("measurements_lastupdated");
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "[Europe/London]");
			StationDeMesurePollution stationDeMesure = new StationDeMesurePollution();

			// obtenir latitude et longitude
			Double latitude = jsonObject.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1);
			Double longitude = jsonObject.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0);

			stationDeMesure = MesureUtils.obtenirStationDeMesurePollutionCorrespondante(latitude, longitude,
					listeDeStationDeMesure);

			MesurePollution mesure = new MesurePollution(id, valeur, typeDonnee, zonedDateTime, stationDeMesure);
			if (mesure != null) {

				listeDesMesures.add(mesure);

			}

		}

		return listeDesMesures;
	}

	/**
	 * Cette methode permet de créer les objets StationDeMesureMeteo à partir de
	 * la réponse des appels APIs utilisés par l'application
	 * 
	 * @param myResponse
	 * @return
	 * @throws JSONException
	 */
	public static List<StationDeMesureMeteo> obtenirLesStationsMeteo(JSONObject myResponse) throws JSONException {

		List<StationDeMesureMeteo> listeDesStationMeteo = new ArrayList<StationDeMesureMeteo>();

		int count = myResponse.getJSONObject("communes").getJSONArray("list").length();

		for (int i = 0; i < count; i++) { // iterate through jsonArray

			Double longitude = myResponse.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("coord").getDouble("Lon");
			Double latitude = myResponse.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("coord").getDouble("Lat");
			StationDeMesureMeteo stationDeMesureMeteo = new StationDeMesureMeteo(latitude, longitude);
			listeDesStationMeteo.add(stationDeMesureMeteo);

		}

		return listeDesStationMeteo;
	}

	/**
	 * Cette methode permet de créer les objets MesureMeteo à partir de la
	 * réponse des appels APIs utilisés par l'application
	 * 
	 * @param myResponseMeteo
	 * @param listeDeStationDeMesure
	 * @return
	 * @throws JSONException
	 */
	public static List<MesureMeteo> obtenirLesMesuresMeteo(JSONObject myResponseMeteo,
			List<StationDeMesureMeteo> listeDeStationDeMesure) throws JSONException {

		List<MesureMeteo> listeDesMesureMeteo = new ArrayList<MesureMeteo>();
		listeDeStationDeMesure.removeAll(Collections.singletonList(null));
		int count = myResponseMeteo.getJSONObject("communes").getJSONArray("list").length();

		for (int i = 0; i < count; i++) { // iterate through jsonArray

			Long id = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i).getLong("id");
			Double longitude = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("coord").getDouble("Lon");
			Double latitude = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("coord").getDouble("Lat");
			StationDeMesureMeteo stationDeMesure = MesureUtils.obtenirStationDeMesureMeteoCorrespondante(latitude,
					longitude, listeDeStationDeMesure);

			String weatherDescription = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONArray("weather").getJSONObject(0).getString("description");
			String weatherIcon = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONArray("weather").getJSONObject(0).getString("icon");
			Double temperature = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("main").getDouble("temp");
			Double pressure = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("main").getDouble("pressure");
			Integer humidity = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("main").getInt("humidity");
			Double tempMin = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("main").getDouble("temp_min");
			Double tempMax = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("main").getDouble("temp_max");
			Double windSpeed = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("wind").getDouble("speed");

			Integer windDegrees = myResponseMeteo.getJSONObject("communes").getJSONArray("list").getJSONObject(i)
					.getJSONObject("wind").optInt("deg");

			MesureMeteo mesureMeteo = new MesureMeteo(id, ZonedDateTime.now(), stationDeMesure, weatherDescription,
					weatherIcon, temperature, pressure, humidity, tempMin, tempMax, windSpeed, windDegrees);
			listeDesMesureMeteo.add(mesureMeteo);

		}
		listeDesMesureMeteo.removeAll(Collections.singletonList(null));
		return listeDesMesureMeteo;
	}

}
