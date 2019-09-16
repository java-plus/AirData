package fr.diginamic;

import fr.diginamic.controller.CommuneController;
import fr.diginamic.controller.UtilisateurController;
import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
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
    private UtilisateurController utilisateurController;

    public StartupDataInit(CommuneController communeController, UtilisateurController utilisateurController) {
        this.communeController = communeController;
        this.utilisateurController = utilisateurController;
    }

    /**
     * initialise la bdd
     */
    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() throws Exception {
        communeController.insererLaListeDesCommunes();
        UtilisateurCreationComptePost utilisateur = new UtilisateurCreationComptePost("user","User44000;","user@user.fr",18,"44001");
        utilisateurController.creerCompte(utilisateur);
        utilisateur = new UtilisateurCreationComptePost("admin","Admin44000;","admin@admin.fr",18,"44001");
        utilisateurController.creerCompteAdmin(utilisateur);
    }

}