package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.service.AnalyseMesureService;
import fr.diginamic.service.MesureMeteoService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.utils.UtilisateurConnecteUtils;

@RestController
@RequestMapping("/mesures")
public class MesureController {

	@Autowired
	private MesurePollutionService mesurePollutionService;

	@Autowired
	private AnalyseMesureService analyseMesureService;

	@Autowired
	private MesureMeteoService mesureMeteoService;

	@GetMapping
	public AnalyseMesureDto recupererHistoriqueIndicateur(@RequestBody AnalyseMesureDto analyseMesureDto) {

		return analyseMesureService.recupererHistoriqueIndicateur(analyseMesureDto);
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
