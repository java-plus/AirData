package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.MesurePollution;
import fr.diginamic.service.MesurePollutionService;

@RestController
@RequestMapping("/mesures")
public class MesureController {

	@Autowired
	MesurePollutionService mesurePollutionService;

	@GetMapping("/pollution")
	public List<MesurePollution> obtenirLesMesuresPollution() {

		String codeCommune = "";
		// TODO retrouver codeCommune enregistré pour l'utilisateur via le
		// context envoyé par le filter
		// context.getCodeCommune= codeCommune;
		return mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);

	}

	@GetMapping(path = "/pollution", params = { "codeCommune" })
	public List<MesurePollution> obtenirLesMesuresPollution(@RequestParam String codeCommune) {

		return mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);

	}

}
