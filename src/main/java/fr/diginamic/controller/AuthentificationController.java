package fr.diginamic.controller;

import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.service.UtilisateurService;
import fr.diginamic.transformer.TransformerUtilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.InfosAuthentificationPost;
import fr.diginamic.service.AuthentificationService;

/**
 * Cette classe gère l'authentifiaction des utilisateurs (afin qu'il puisse
 * obtenir le cookie qui leur permettra d'acceder aux urls de l'application.
 * Elle contient la methode authenticate(@RequestBody InfosAuthentificationPost
 * infos) accessible via l'url "/auth"
 * 
 * @author Diginamic02
 *
 */
@RestController
public class AuthentificationController {

	/**
	 * un service d’authentification
	 */
	private AuthentificationService authentificationService;

	/**
	 * un service d’utilisateur
	 */
	private UtilisateurService utilisateurService;

	/**
	 * un transformer d’utilisateur
	 */
	private TransformerUtilisateur transformerUtilisateur;

	/**
	 *
	 * @param authentificationService
	 * @param utilisateurService
	 * @param transformerUtilisateur
	 */
	public AuthentificationController(AuthentificationService authentificationService, UtilisateurService utilisateurService, TransformerUtilisateur transformerUtilisateur) {
		this.authentificationService = authentificationService;
		this.utilisateurService = utilisateurService;
		this.transformerUtilisateur = transformerUtilisateur;
	}

	/**
	 * Cette methode gère le POST sur l'url /auth qui permet à l'utilisateur
	 * d'obtenir (ou non) le cookie qui lui permettra d'être authentifié.
	 * 
	 * @param infos information d’authentification
	 * @return une responseEntity
	 */
	@PostMapping("/auth")
	public ResponseEntity<?> authenticate(@RequestBody InfosAuthentificationPost infos) {
		return authentificationService.authenticate(infos);
	}

	/**
	 * permet de recuperer l’utilisateur courant
	 * @return un UtilisateurDto
	 */
	@GetMapping("/auth/user")
	public UtilisateurDto recupererUtilisateur(){
		return transformerUtilisateur.UtilisateurToUtilisateurDto(utilisateurService.recupererUtilisateur());
	}

}
