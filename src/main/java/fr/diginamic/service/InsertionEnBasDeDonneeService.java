package fr.diginamic.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;
import fr.diginamic.repository.StationMeteoRepository;
import fr.diginamic.repository.StationPollutionRepository;
import fr.diginamic.utils.ApiUtils;
import fr.diginamic.utils.JsonManipulation;

/**
 * Classe insererEnBaseToutesLes24h Cette classe est un service gère les
 * insertions en base de donnée Elle fait appel aux APIs, trie et transforme les
 * données puis les envoie vers les Repository adequats.
 * 
 * @author Diginamic02
 *
 */
@Service
@EnableScheduling
public class InsertionEnBasDeDonneeService {

	@Autowired
	CommuneService communeService;
	@Autowired
	MesurePollutionService mesurePollutionService;
	@Autowired
	MesureMeteoService mesureMeteoService;
	@Autowired
	StationMeteoRepository stationMeteoRepository;
	@Autowired
	StationPollutionRepository stationPollutionRepository;

	@Scheduled(fixedDelay = 100000)
	/**
	 * Methode insererEnBaseToutesLes24h() Cette methode s'active
	 * automatiquement (grâce à un @Schedule) tous les X temps pour vérifier si
	 * de nouvelles données sont disponibles sur les APIs. Elle appelle les
	 * APIs, trie et transforme les données pour les transformer en MesureMeteo
	 * et MesurePollution puis vérifie via createOrNotMesureMeteo(mesureMeteo)
	 * et createOrNotMesurePollution(MesurePollution mesurePollution) dans le
	 * Repository si la mesure existe déjà, si ce n'est pas le cas, elle
	 * l'insère en base, sinon elle ne fait rien et passe à la suivante
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Methode createOrNotMesureMeteo(MesureMeteo mesureMeteo) Cette methode
	 * vérifie si la MesureMeteo existe deja dans la base de donnée et si ce
	 * n'est pas le cas, l'insere en base.
	 * 
	 * @param mesureMeteo
	 */
	private void createOrNotMesureMeteo(MesureMeteo mesureMeteo) {
		if (!mesureMeteoService.obtenirMesureMeteo(mesureMeteo).isPresent()) {
			mesureMeteo.setStationDeMesure(stationMeteoRepository.findByLatitudeAndLongitude(
					mesureMeteo.getStationDeMesure().getLatitude(), mesureMeteo.getStationDeMesure().getLongitude()));
			mesureMeteoService.mettreEnBaseMesureMeteo(mesureMeteo);
		}

	}

	/**
	 * Methode createOrNotMesurePollution(MesurePollution mesurePollution) Cette
	 * methode vérifie si la MesurePollution existe deja dans la base de donnée
	 * et si ce n'est pas le cas, l'insere en base.
	 * 
	 * @param mesurePollution
	 */
	private void createOrNotMesurePollution(MesurePollution mesurePollution) {
		if (!mesurePollutionService.obtenirMesurePollution(mesurePollution).isPresent()) {
			mesurePollution.setStationDeMesure(stationPollutionRepository.findByLatitudeAndLongitude(
					mesurePollution.getStationDeMesure().getLatitude(),
					mesurePollution.getStationDeMesure().getLongitude()));
			mesurePollutionService.mettreEnBaseMesurePollution(mesurePollution);
		}
		;

	}

}
