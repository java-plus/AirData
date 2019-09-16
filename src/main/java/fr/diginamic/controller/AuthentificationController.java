package fr.diginamic.controller;

import org.springframework.http.ResponseEntity;
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

	private AuthentificationService authentificationService;

	public AuthentificationController(AuthentificationService authentificationService) {
		this.authentificationService = authentificationService;
	}

	/**
	 * Cette methode gère le POST sur l'url /auth qui permet à l'utilisateur
	 * d'obtenir (ou non) le cookie qui lui permettra d'être authentifié.
	 * 
	 * @param infos
	 * @return
	 */
	@PostMapping("/auth")
	public ResponseEntity<?> authenticate(@RequestBody InfosAuthentificationPost infos) {
		return authentificationService.authenticate(infos);
	}
}
