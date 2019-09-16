package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Commune;
import fr.diginamic.service.CommuneService;
import fr.diginamic.service.MesureMeteoService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.service.StationDeMesureMeteoService;
import fr.diginamic.service.StationDeMesurePollutionService;

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
	 *  Un service de mesure de pollution
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
