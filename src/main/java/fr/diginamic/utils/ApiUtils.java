package fr.diginamic.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * Cette classe permet d'appeler les APIs utilisées par l'application. Elle est
 * principalement utilisée dans la classe InsertionEnBaseDeDonneeService().
 * 
 * @author Diginamic02
 *
 */
public class ApiUtils {

	public static JSONObject callApiGeojsonCommunes(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print in String

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(response.toString());

		return myResponse;
	}

	/**
	 * Cette methode permet d'appeler l'API
	 * https://public.opendatasoft.com/api/records/1.0/search/?dataset=openaq
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject callApiPollution(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print in String

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(response.toString());

		return myResponse;
	}

	/**
	 * Cette methode permet d'appeler l'API
	 * https://geo.api.gouv.fr/communes?codeRegion=52&fields=nom,code,codesPostaux,centre,codeRegion,population&format=json&geometry=centre
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject callApiCommunes(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		// print in String
		String maReponseDefinitive = "{\"communes\":" + response.toString() + "}";
		// .substring(1, response.toString().length()-1) ;

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(maReponseDefinitive);

		return myResponse;
	}

	/**
	 * Cette methode permet d'appeler l'API
	 * http://api.openweathermap.org/data/2.5/box/city?bbox=-2.48291015625,46.29001987172955,1.2139892578125,48.25028349849022,100&appid=cf994ca322a654d044fd952ce00569fe
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject callApiMeteo(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		// print in String
		String maReponseDefinitive = "{\"communes\":" + response.toString() + "}";
		// .substring(1, response.toString().length()-1) ;

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(maReponseDefinitive);

		return myResponse;

	}
}
