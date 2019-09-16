package fr.diginamic.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.controller.dto.UtilisateurRgpdDto;
import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Role;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.service.UtilisateurService;
import fr.diginamic.transformer.TransformerUtilisateur;
import fr.diginamic.utils.UtilisateurConnecteUtils;

/**
 * Cette classe gère les appels des urls concernant les utilisateurs de
 * l'application.
 * 
 * @author Diginamic02
 *
 */
@RestController
public class UtilisateurController {

	private TransformerUtilisateur transformerUtilisateur;
	private UtilisateurService utilisateurService;

	public UtilisateurController(TransformerUtilisateur transformerUtilisateur, UtilisateurService utilisateurService) {
		this.transformerUtilisateur = transformerUtilisateur;
		this.utilisateurService = utilisateurService;
	}

	/**
	 * Cette methode gère l'url /compte en POST qui permet de créer un
	 * compteUtilisateur en fonction des informations présentes dans le corps de
	 * la requête. Elle transforme d'abord le corps de la requete en objet
	 * Utilisateur. Ensuite elle insère en base cet utilisateur puis renvoi un
	 * objet UtilisateurDTO
	 * 
	 * @param utilisateurCreationComptePost
	 * @return
	 */
	@PostMapping("/compte")
	public UtilisateurDto creerCompte(@Valid @RequestBody UtilisateurCreationComptePost utilisateurCreationComptePost) {

		Utilisateur utilisateur = transformerUtilisateur
				.UtilisateurCreationComptePostToUtilisateur(utilisateurCreationComptePost);
		utilisateurService.insererEnBase(utilisateur);
		return transformerUtilisateur.UtilisateurToUtilisateurDto(utilisateur);

	}

	/**
	 * Cette methode gère l'url /compte_admin en POST qui permet de créer un
	 * compteUtilisateur en fonction des informations présentes dans le corps de
	 * la requête et dont le rôle est ROLE_ADMIN. Elle transforme d'abord le
	 * corps de la requete en objet Utilisateur. Elle rajoute à la liste des
	 * rôles de cet utilisateur un rôle ROLE_ADMIN. Ensuite elle insère en base
	 * cet utilisateur puis renvoi un objet UtilisateurDTO
	 * 
	 * @param utilisateurCreationComptePost
	 * @return
	 */
	@PostMapping("/compte_admin")
	public UtilisateurDto creerCompteAdmin(
			@Valid @RequestBody UtilisateurCreationComptePost utilisateurCreationComptePost) {
		Utilisateur utilisateur = transformerUtilisateur
				.UtilisateurCreationComptePostToUtilisateur(utilisateurCreationComptePost);
		utilisateur.getRole().add(new Role("ROLE_ADMIN"));
		utilisateurService.insererEnBase(utilisateur);
		return transformerUtilisateur.UtilisateurToUtilisateurDto(utilisateur);
	}

	/**
	 * Cette methode gère l'url /compte en GET qui permet d'obtenir un objet
	 * CompteUtilisateur correspondant à l'utilisateur courant
	 * 
	 * @return
	 */
	@GetMapping("/compte")
	public CompteUtilisateur obtenirCompteUtilisateur() {
		return utilisateurService.obtenirCompteUtilisateur();
	}

	/**
	 * Cette methode gère l'url /compte en PATCH qui permet de modifier les
	 * informations présentes en BDD concernant un Utilisateur.
	 * 
	 * @param compteUtilisateur
	 * @return
	 */
	@PatchMapping("/compte")
	public CompteUtilisateur modifierCompteUtilisateur(@RequestBody CompteUtilisateur compteUtilisateur) {

		return utilisateurService.modifierCompteUtilisateur(compteUtilisateur);

	}

	/**
	 * Cette methode gère l'url /compte en DELETE qui permet de supprimer les
	 * informations présentes en BDD concernant un Utilisateur.
	 * 
	 * @return
	 */
	@DeleteMapping("/compte")
	public ResponseEntity<?> supprimerCompte() {
		String identifiant = UtilisateurConnecteUtils.recupererIdentifiant();
		return utilisateurService.supprimerCompte(identifiant);
	}

	/**
	 * Cette methode gère l'url /compte_avec_admin en DELETE qui permet de
	 * supprimer les informations présentes en BDD concernant un Utilisateur
	 * dont l'un des rôle est ROLE_ADMIN.
	 * 
	 * @param identifiant
	 * @return
	 */
	@DeleteMapping("/compte_avec_admin")
	public ResponseEntity<?> supprimerCompteAvecAdmin(@RequestParam String identifiant) {
		return utilisateurService.supprimerCompte(identifiant);
	}

	/**
	 * Cette methode gère l'url /utilisateur_rgpd en GET qui permet d'obtenir un
	 * objet UtilisateurRgpdDto afin que l'utilisateur ait accès aux
	 * informations le concernant en BDD
	 * 
	 * @return
	 */
	@GetMapping("/utilisateur_rgpd")
	public UtilisateurRgpdDto recupererUtilisateur() {
		return transformerUtilisateur.utilisateurToUtilisateurRgpdDto(utilisateurService.recupererUtilisateur());
	}
}
