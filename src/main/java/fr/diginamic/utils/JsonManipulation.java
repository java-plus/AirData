package fr.diginamic.utils;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;

public class JsonManipulation {

	public static List<Commune> obtenirLesCommunes(JSONObject myResponse) throws JSONException {

		List<Commune> listeDesCommunes = new ArrayList<Commune>();
		JSONArray tableauDesCommunes = myResponse.getJSONArray("communes");

		int count = tableauDesCommunes.length();
		for (int i = 0; i < count; i++) { // iterate through jsonArray
			// for (Object object : tableauDesCommunes) {
			// JSONObject commune = (JSONObject) object;
			Commune communeTableau = new Commune(tableauDesCommunes.getJSONObject(i).getString("nom"),
					tableauDesCommunes.getJSONObject(i).getString("code"),
					tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
							.getDouble(1),
					tableauDesCommunes.getJSONObject(i).getJSONObject("centre").getJSONArray("coordinates")
							.getDouble(0),
					tableauDesCommunes.getJSONObject(i).getInt("population"));

			listeDesCommunes.add(communeTableau);
		}

		return listeDesCommunes;

	}

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
			// System.out.println("jsonObject " + i + " ------ " + jsonObject);

			JSONObject jsonObjectGeometry = jsonObject.getJSONObject("geometry");
			Double latitude = (Double) jsonObjectGeometry.getJSONArray("coordinates").get(1);
			// System.out.println(jsonObjectGeometry.getJSONArray("coordinates").get(0));
			Double longitude = (Double) jsonObjectGeometry.getJSONArray("coordinates").get(0);
			// System.out.println(jsonObjectGeometry.getJSONArray("coordinates").get(1));

			JSONObject jsonObjectFields = jsonObject.getJSONObject("fields");
			String typeDonnee = jsonObjectFields.getString("measurements_parameter");
			// System.out.println(typeDonnee);

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

	public static List<MesurePollution> obtenirLesMesures(JSONObject myResponse,
			List<StationDeMesurePollution> listeDeStationDeMesure) throws JSONException {
		// TODO Auto-generated method stub

		List<MesurePollution> listeDesMesures = new ArrayList<MesurePollution>();

		int count = myResponse.getJSONArray("records").length();

		for (int i = 0; i < count; i++) { // iterate through jsonArray

			JSONObject jsonObject = myResponse.getJSONArray("records").getJSONObject(i); // get
																							// jsonObject
																							// @
																							// i
																							// position

			JSONObject jsonObjectFields = jsonObject.getJSONObject("fields");
			String typeDonnee = jsonObjectFields.getString("measurements_parameter");
			double valeur = jsonObjectFields.getDouble("measurements_value");
			String date = jsonObjectFields.getString("measurements_lastupdated");
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(date + "[Europe/London]");
			StationDeMesurePollution stationDeMesure = new StationDeMesurePollution();

			// obtenir latitude et longitude
			Double latitude = jsonObject.getJSONObject("geometry").getJSONArray("coordinates").getDouble(1);
			Double longitude = jsonObject.getJSONObject("geometry").getJSONArray("coordinates").getDouble(0);

			stationDeMesure = MesureUtils.obtenirStationDeMesureCorrespondante(latitude, longitude,
					listeDeStationDeMesure);

			MesurePollution mesure = new MesurePollution(valeur, typeDonnee, zonedDateTime, stationDeMesure);
			if (mesure != null) {

				listeDesMesures.add(mesure);

			}

		}

		return listeDesMesures;
	}

	public static List<StationDeMesureMeteo> obtenirLesStationsMeteo(JSONObject myResponse) throws JSONException {
		// TODO Auto-generated method stub

		List<StationDeMesureMeteo> listeDesStationMeteo = new ArrayList<StationDeMesureMeteo>();

		// JSONArray longitude =
		// myResponse.getJSONObject("communes").getJSONArray("list");
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

}
