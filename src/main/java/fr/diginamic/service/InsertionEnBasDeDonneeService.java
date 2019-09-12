package fr.diginamic.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;
import fr.diginamic.utils.ApiUtils;
import fr.diginamic.utils.JsonManipulation;

@Service
@EnableScheduling
public class InsertionEnBasDeDonneeService {

	@Autowired
	CommuneService communeService;
	@Autowired
	MesurePollutionService mesurePollutionService;
	@Autowired
	MesureMeteoService mesureMeteoService;

	// @Scheduled(fixedDelay = 1000)
	public void insererEnBaseToutesLes24h() throws Exception {
		// System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		JSONObject myResponse = ApiUtils.callApiPollution(
				"https://public.opendatasoft.com/api/records/1.0/search/?dataset=openaq&rows=1500&sort=measurements_lastupdated&facet=location&facet=measurements_parameter&facet=measurements_sourcename&facet=measurements_lastupdated&geofilter.polygon=(46.29001987172955%2C-2.48291015625)%2C(48.25028349849022%2C-2.48291015625)%2C(48.25028349849022%2C1.2139892578125)%2C(46.29001987172955%2C1.2139892578125)%2C(46.29001987172955%2C-2.48291015625)");
		List<StationDeMesurePollution> listeDeStationsDeMesure = JsonManipulation
				.obtenirLesStationDeMesures(myResponse);
		List<MesurePollution> listeDesMesuresPollution = new ArrayList<MesurePollution>();
		listeDesMesuresPollution = JsonManipulation.obtenirLesMesures(myResponse, listeDeStationsDeMesure);
		for (MesurePollution mesurePollution : listeDesMesuresPollution) {
			createOrNotMesurePollution(mesurePollution);
		}

		JSONObject myResponseMeteo = ApiUtils.callApiMeteo(
				"http://api.openweathermap.org/data/2.5/box/city?bbox=-2.48291015625,46.29001987172955,1.2139892578125,48.25028349849022,100&appid=cf994ca322a654d044fd952ce00569fe");
		List<StationDeMesureMeteo> listeDeStationsDeMesureMeteo = new ArrayList<StationDeMesureMeteo>();
		listeDeStationsDeMesureMeteo = JsonManipulation.obtenirLesStationsMeteo(myResponseMeteo);

		/////////////////// OBTENTION DE LA LISTE DES MESURES METEO DISPONIBLES
		/////////////////// SUR OMPENWEATHERMAP//////////////////////
		List<MesureMeteo> listeDeMesureMeteo = JsonManipulation.obtenirLesMesuresMeteo(myResponseMeteo,
				listeDeStationsDeMesureMeteo);
		for (MesureMeteo mesureMeteo : listeDeMesureMeteo) {
			createOrNotMesureMeteo(mesureMeteo);
		}
	}

	private void createOrNotMesureMeteo(MesureMeteo mesureMeteo) {
		// TODO Auto-generated method stub
		mesureMeteoService.obtenirMesurePollution(mesurePollution);

	}

	private void createOrNotMesurePollution(MesurePollution mesurePollution) {
		// TODO Auto-generated method stub
		mesurePollutionService.obtenirMesurePollution(mesurePollution);

	}

}
