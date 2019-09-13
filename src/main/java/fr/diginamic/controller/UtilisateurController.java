package fr.diginamic.controller;

import fr.diginamic.controller.dto.UtilisateurConnecteService;
import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.controller.dto.UtilisateurRgpdDto;
import fr.diginamic.entites.Role;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.transformer.TransformerUtilisateur;
import fr.diginamic.utils.UtilisateurConnecteUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.service.UtilisateurService;

@RestController
public class UtilisateurController {

    private TransformerUtilisateur transformerUtilisateur;
    private UtilisateurService utilisateurService;

    public UtilisateurController(TransformerUtilisateur transformerUtilisateur,UtilisateurService utilisateurService) {
        this.transformerUtilisateur = transformerUtilisateur;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/compte")
    public UtilisateurDto creerCompte(@Valid @RequestBody UtilisateurCreationComptePost utilisateurCreationComptePost){

        Utilisateur utilisateur = transformerUtilisateur.UtilisateurCreationComptePostToUtilisateur(utilisateurCreationComptePost);
        utilisateurService.insererEnBase(utilisateur);
        return transformerUtilisateur.UtilisateurToUtilisateurDto(utilisateur);

    }

    @PostMapping("/compte_admin")
    public UtilisateurDto creerCompteAdmin(@Valid @RequestBody UtilisateurCreationComptePost utilisateurCreationComptePost){
        Utilisateur utilisateur = transformerUtilisateur.UtilisateurCreationComptePostToUtilisateur(utilisateurCreationComptePost);
        utilisateur.getRole().add(new Role("ROLE_ADMIN"));
        utilisateurService.insererEnBase(utilisateur);
        return transformerUtilisateur.UtilisateurToUtilisateurDto(utilisateur);
    }


	@GetMapping("/compte")
	public CompteUtilisateur obtenirCompteUtilisateur() {
		return utilisateurService.obtenirCompteUtilisateur();
	}

	@PatchMapping("/compte")
    public CompteUtilisateur modifierCompteUtilisateur(@RequestBody CompteUtilisateur compteUtilisateur){

        return utilisateurService.modifierCompteUtilisateur(compteUtilisateur);

    }

    @DeleteMapping("/compte")
    public ResponseEntity<?> supprimerCompte(){
        String identifiant =UtilisateurConnecteUtils.recupererIdentifiant();
        return utilisateurService.supprimerCompte(identifiant);
    }

    @DeleteMapping("/compte_avec_admin")
    public ResponseEntity<?> supprimerCompteAvecAdmin(@RequestParam String identifiant ){
        return utilisateurService.supprimerCompte(identifiant);
    }


    @GetMapping("/utilisateur_rgpd")
    public UtilisateurRgpdDto recupererUtilisateur(){
        return transformerUtilisateur.utilisateurToUtilisateurRgpdDto(utilisateurService.recupererUtilisateur());
    }
}
