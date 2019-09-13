package fr.diginamic.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.entites.StationDeMesurePollution;
import fr.diginamic.service.CommuneService;
import fr.diginamic.service.MesureMeteoService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.utils.ApiUtils;
import fr.diginamic.utils.CommuneUtils;
import fr.diginamic.utils.JsonManipulation;

@RestController
@RequestMapping("/communes")
public class CommuneController {

	@Autowired
	CommuneService communeService;
	@Autowired
	MesurePollutionService mesurePollutionService;
	@Autowired
	MesureMeteoService mesureMeteoService;

	@GetMapping
	public List<Commune> obtenirLaListeDesCommunes() {
		return communeService.obtenirLaListeDesCommunes();
	}

	@GetMapping("/insertion")
	public String insererLaListeDesCommunes() throws Exception {
		/////////////////// OBTENTION DE LA LISTE DES STATIONS DE
		/////////////////// MESURE POLLUTION//////////////////////
		JSONObject myResponse = ApiUtils.callApiPollution(
				"https://public.opendatasoft.com/api/records/1.0/search/?dataset=openaq&rows=1500&sort=measurements_lastupdated&facet=location&facet=measurements_parameter&facet=measurements_sourcename&facet=measurements_lastupdated&geofilter.polygon=(46.29001987172955%2C-2.48291015625)%2C(48.25028349849022%2C-2.48291015625)%2C(48.25028349849022%2C1.2139892578125)%2C(46.29001987172955%2C1.2139892578125)%2C(46.29001987172955%2C-2.48291015625)");
		List<StationDeMesurePollution> listeDeStationsDeMesure = JsonManipulation
				.obtenirLesStationDeMesures(myResponse);

		/////////////////// OBTENTION DE LA LISTE DES COMMUNES DISPONIBLES SUR
		/////////////////// GEO DATA//////////////////////
		JSONObject myResponseCommunes = ApiUtils.callApiCommunes(
				"https://geo.api.gouv.fr/communes?codeRegion=52&fields=nom,code,codesPostaux,centre,codeRegion,population&format=json&geometry=centre");
		List<Commune> listeDesCommunes = new ArrayList<Commune>();
		listeDesCommunes = JsonManipulation.obtenirLesCommunes(myResponseCommunes);

		/////////////////// MISE EN RELATION DES COMMUNES DISPONIBLES SUR GEO
		/////////////////// DATA ET DES STATIONS DE
		/////////////////// MESURES//////////////////////
		listeDesCommunes = CommuneUtils.obtenirLesStationsDeMesuresLesPlusProches(listeDesCommunes,
				listeDeStationsDeMesure);

		/////////////////// OBTENTION DE LA LISTE DES
		/////////////////// MESURES POLLUTION//////////////////////
		List<MesurePollution> listeDesMesuresPollution = new ArrayList<MesurePollution>();
		listeDesMesuresPollution = JsonManipulation.obtenirLesMesures(myResponse, listeDeStationsDeMesure);

		/////////////////// OBTENTION DE LA LISTE DES STATIONS METEO DISPONIBLES
		/////////////////// SUR OMPENWEATHERMAP//////////////////////
		JSONObject myResponseMeteo = ApiUtils.callApiMeteo(
				"http://api.openweathermap.org/data/2.5/box/city?bbox=-2.48291015625,46.29001987172955,1.2139892578125,48.25028349849022,100&appid=cf994ca322a654d044fd952ce00569fe");
		List<StationDeMesureMeteo> listeDeStationsDeMesureMeteo = new ArrayList<StationDeMesureMeteo>();
		listeDeStationsDeMesureMeteo = JsonManipulation.obtenirLesStationsMeteo(myResponseMeteo);
		listeDesCommunes = CommuneUtils.obtenirLesStationsDeMesuresMeteoLesPlusProches(listeDesCommunes,
				listeDeStationsDeMesureMeteo);

		/////////////////// OBTENTION DE LA LISTE DES MESURES METEO DISPONIBLES
		/////////////////// SUR OMPENWEATHERMAP//////////////////////
		List<MesureMeteo> listeDeMesureMeteo = JsonManipulation.obtenirLesMesuresMeteo(myResponseMeteo,
				listeDeStationsDeMesureMeteo);
		/////////////////// INSERTION EN BASE//////////////////////

		communeService.insererEnBas(listeDesCommunes);

		for (Iterator<MesurePollution> iter = listeDesMesuresPollution.listIterator(); iter.hasNext();) {
			MesurePollution a = iter.next();
			if (a.getStationDeMesure().getId() == null) {
				iter.remove();
			}
		}
		mesurePollutionService.insererEnBase(listeDesMesuresPollution);
		for (Iterator<MesureMeteo> iter = listeDeMesureMeteo.listIterator(); iter.hasNext();) {
			MesureMeteo a = iter.next();
			if (a.getStationDeMesure().getId() == null) {
				iter.remove();
			}
		}
		mesureMeteoService.insererEnBase(listeDeMesureMeteo);
		return "ok";
	}
}
