package fr.diginamic;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import fr.diginamic.controller.CommuneController;

/**
 * Classe initialisant la bdd
 */
@Component
public class StartupDataInit {

	private CommuneController communeController;

	public StartupDataInit(CommuneController communeController) {
		this.communeController = communeController;
	}

	/**
	 * initialise la bdd
	 */
	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() throws Exception {
		communeController.insererLaListeDesCommunes();
	}

}