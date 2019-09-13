package fr.diginamic;

import fr.diginamic.controller.CommuneController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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