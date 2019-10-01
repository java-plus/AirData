package fr.diginamic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import fr.diginamic.controller.UtilisateurController;
import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.service.InsertionEnBasDeDonneeService;

/**
 * Cette Classe initialise la bdd. Au demmarage de l'application, elle fait
 * appel à la methode init().
 * 
 */
@Component
public class StartupDataInit {


	@Value("${data.init}")
	private Boolean isDataInit;

	private InsertionEnBasDeDonneeService insertionEnBasDeDonneeService;

	private UtilisateurController utilisateurController;

	public StartupDataInit(InsertionEnBasDeDonneeService insertionEnBasDeDonneeService,
			UtilisateurController utilisateurController) {
		this.insertionEnBasDeDonneeService = insertionEnBasDeDonneeService;
		this.utilisateurController = utilisateurController;
	}

	/**
	 * initialise la bdd Cette méthode init va être invoquée au démarrage de
	 * l'application. via la methode insererLaListeDesCommunes() issue de la
	 * classe InsertionEnBasDeDonneeService, elle fait des appels aux APIs et
	 * récupère la liste des stations metéo, des stations de pollutions, des
	 * communes des mesures de pollution et de météo puis elle les lie entre
	 * elles et les insère en BDD
	 */
	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() throws Exception {

		if(isDataInit) {
			insertionEnBasDeDonneeService.insererLaListeDesCommunes();
			UtilisateurCreationComptePost utilisateur = new UtilisateurCreationComptePost("user", "User44000;",
					"user@user.fr", 18, "44001");
			utilisateurController.creerCompte(utilisateur);
			utilisateur = new UtilisateurCreationComptePost("admin", "Admin44000;", "admin@admin.fr", 18, "44001");
			utilisateurController.creerCompteAdmin(utilisateur);
		}

	}

}
