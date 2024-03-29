package fr.diginamic.controller;

import fr.diginamic.entites.Alerte;
import fr.diginamic.service.AlerteService;
import fr.diginamic.service.IAlerteService;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Cette classe gère les methodes permettant la création d'alertes (pollution
 * oou météo) par un admin.
 *
 * @author Diginamic02
 *
 */
@RestController
public class AlerteController {


	/**
	 * Interface implementée par un service d’alerte
	 */
	private IAlerteService alerteService;

	/**
	 *
	 * @param alerteService un service d’alerte
	 */
	public AlerteController(IAlerteService alerteService) {
		this.alerteService = alerteService;
	}

	/**
	 * Cette Methode permet la création d'une alerte qui sera stockée en BDD
	 *
	 * @param alerte
	 * @return
	 */
	@PostMapping("/alerte")
	public Alerte creerAlerte(@Valid @RequestBody Alerte alerte) {
		return alerteService.creerAlerte(alerte);
	}

	/**
	 * Cette methode permet d'obtenir la liste des alertes présentes en BDD
	 *
	 * @param alerte
	 * @return
	 */
	@PostMapping("/recupereralertes")
	public List<Alerte> recupererAlertes(@RequestBody Alerte alerte) {
		return alerteService.recupererAlerte(alerte);
	}
}
