package fr.diginamic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.controller.dto.AnalyseMesureDtoPost;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.service.AnalyseMesureService;
import fr.diginamic.service.MesureMeteoService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.utils.UtilisateurConnecteUtils;

@RestController
@RequestMapping("/mesures")
public class MesureController {

	private MesurePollutionService mesurePollutionService;
	private AnalyseMesureService analyseMesureService;
	private MesureMeteoService mesureMeteoService;

	/**
	 * Constructor
	 * 
	 * @param mesurePollutionService
	 * @param analyseMesureService
	 * @param mesureMeteoService
	 */
	public MesureController(MesurePollutionService mesurePollutionService, AnalyseMesureService analyseMesureService, MesureMeteoService mesureMeteoService) {
		this.mesurePollutionService = mesurePollutionService;
		this.analyseMesureService = analyseMesureService;
		this.mesureMeteoService = mesureMeteoService;
	}

	/**
	 * Méthode qui récupère l'historique d'un indicateur (date et valeur) sur une période donnée.
	 *
	 * @see AnalyseMesureDtoPost
	 * 
	 * @param analyseMesureDtoPost
	 *            Le Json envoyé dans le body de la requète GET, sa structure doit correspondre à la classe AnalyseMesureDtoPost
	 * @return AnalyseMesureDto
	 * 
	 */
	@GetMapping
	public AnalyseMesureDto recupererHistoriqueIndicateur(@RequestBody AnalyseMesureDtoPost analyseMesureDtoPost) {
		return analyseMesureService.recupererHistoriqueIndicateur(analyseMesureDtoPost);
	}

	@GetMapping("/pollution")
	public List<MesurePollution> obtenirLesMesuresPollution() {
		String codeCommune = UtilisateurConnecteUtils.recupererCodeCommune();
		return mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);

	}

	@GetMapping(path = "/pollution", params = { "codeCommune" })
	public List<MesurePollution> obtenirLesMesuresPollution(@RequestParam String codeCommune) {
		return mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);
	}

	@GetMapping("/meteo")
	public List<MesureMeteo> obtenirLesMesuresDeMeteo() {
		String codeCommune = UtilisateurConnecteUtils.recupererCodeCommune();
		return mesureMeteoService.obtenirLesMesuresDeMeteo(codeCommune);
	}

	@GetMapping(path = "/meteo", params = { "codeCommune" })
	public List<MesureMeteo> obtenirLesMesuresMeteo(@RequestParam String codeCommune) {
		return mesureMeteoService.obtenirLesMesuresDeMeteo(codeCommune);
	}

}
