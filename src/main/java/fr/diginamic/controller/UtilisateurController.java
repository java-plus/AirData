package fr.diginamic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.service.UtilisateurService;

@RestController
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@GetMapping
	public CompteUtilisateur obtenirCompteutilisateur() {

		// TODO trouver login dans context
		String login = "";

		return utilisateurService.obtenirCompteUtilisateur(login);

	}

}
