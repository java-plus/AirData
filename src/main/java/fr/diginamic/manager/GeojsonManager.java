package fr.diginamic.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.CommuneMesurePollution;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.service.CommuneService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.utils.ApiUtils;
import fr.diginamic.utils.JsonManipulation;

/**
 * Cette classe gère la récupération du geoJson en BDD
 *
 * @author Diginamic02
 *
 */
@Service
public class GeojsonManager {

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
	 * Cette methode permet d'obtenir la liste complète des communes présentes
	 * en BDD complétées par ses coordonnées polygonales pour geojson et des
	 * données de pollutions
	 * 
	 * @return
	 */
	public String obtenirLaListeDesCommunesAvecPollution() throws Exception {
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
}
