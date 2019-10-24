package fr.diginamic.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.CommuneMesurePollution;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.service.CommuneService;
import fr.diginamic.service.MesureMeteoService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.service.StationDeMesureMeteoService;
import fr.diginamic.service.StationDeMesurePollutionService;
import fr.diginamic.utils.ApiUtils;
import fr.diginamic.utils.JsonManipulation;

/**
 * Cette classe les appels faits grâce aux url /communes et gère donc les objets
 * Commune présent en BDD
 * 
 * @author Diginamic02
 *
 */
@RestController
@RequestMapping("/communes")
public class CommuneController {

	/**
	 * Un service de commune
	 */
	@Autowired
	CommuneService communeService;
	/**
	 * Un service de mesure de pollution
	 */
	@Autowired
	MesurePollutionService mesurePollutionService;
	/**
	 * Un service de mesure de meteo
	 */
	@Autowired
	MesureMeteoService mesureMeteoService;
	/**
	 * Un service de station de mesure
	 */
	@Autowired
	StationDeMesureMeteoService stationDeMesureMeteoService;
	/**
	 * Un service de station de pollution
	 */
	@Autowired
	StationDeMesurePollutionService stationDeMesurePollutionService;

	/**
	 * Cette methode permet d'obtenir la liste complète des communes présentes
	 * en BDD
	 * 
	 * @return
	 */
	@GetMapping
	public List<Commune> obtenirLaListeDesCommunes() {
		return communeService.obtenirLaListeDesCommunes();
	}

	/**
	 * Cette methode permet d'obtenir la liste complète des communes présentes
	 * en BDD
	 * 
	 * @return
	 */
	@GetMapping("/geojson")
	public String obtenirLaListeDesCommunesPm10() throws Exception {
		List<Commune> listeDesCommunes = communeService.obtenirLaListeDesCommunes();
		List<CommuneMesurePollution> listeDesCommunesMesure = new ArrayList<CommuneMesurePollution>();
		for (Commune commune : listeDesCommunes) {
			List<MesurePollution> listeDesMesuresPollution = mesurePollutionService
					.obtenirLesMesuresDePollution(commune.getCodeCommune());
			double mesurePollutionPm10 = 0;
			double mesurePollutionPm25 = 0;
			double mesurePollutionSO2 = 0;
			double mesurePollutionNO2 = 0;
			double mesurePollutionCO = 0;
			double mesurePollutionO3 = 0;
			for (MesurePollution mesurePollution : listeDesMesuresPollution) {
				if (mesurePollution.getTypeDeDonnee().equals("PM10")) {
					mesurePollutionPm10 = mesurePollution.getValeur();
				}
				if (mesurePollution.getTypeDeDonnee().equals("PM2.5")) {
					mesurePollutionPm25 = mesurePollution.getValeur();
				}
				if (mesurePollution.getTypeDeDonnee().equals("SO2")) {
					mesurePollutionSO2 = mesurePollution.getValeur();
				}
				if (mesurePollution.getTypeDeDonnee().equals("NO2")) {
					mesurePollutionNO2 = mesurePollution.getValeur();
				}
				if (mesurePollution.getTypeDeDonnee().equals("CO")) {
					mesurePollutionCO = mesurePollution.getValeur();
				}
				if (mesurePollution.getTypeDeDonnee().equals("O3")) {
					mesurePollutionO3 = mesurePollution.getValeur();
				}
			}
			listeDesCommunesMesure.add(new CommuneMesurePollution(commune.getCodeCommune(), mesurePollutionPm10,
					mesurePollutionPm25, mesurePollutionNO2, mesurePollutionSO2, mesurePollutionCO, mesurePollutionO3));

		}

		JSONObject jSONObject = ApiUtils.callApiGeojsonCommunes(
				"https://raw.githubusercontent.com/gregoiredavid/france-geojson/master/regions/pays-de-la-loire/communes-pays-de-la-loire.geojson");
		return JsonManipulation.obtenirGeoJson2(jSONObject, listeDesCommunesMesure).toString();
	}

	/**
	 * Cette methode permet d'obtenir une commune présente en BDD en fonction de
	 * son code Commune
	 * 
	 * @param codeCommune
	 * @return
	 */
	@GetMapping(params = { "codeCommune" })
	public Commune obtenirUneCommuneEnFonctionCodeCommune(@RequestParam String codeCommune) {
		return communeService.trouverCommuneParCode(codeCommune);
	}

}
