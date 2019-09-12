package fr.diginamic.controller;

import fr.diginamic.controller.dto.UtilisateurConnecteService;
import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.transformer.TransformerUtilisateur;
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


	@GetMapping("/compte")
	public CompteUtilisateur obtenirCompteUtilisateur() {

        String login = ((UtilisateurConnecteService)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return utilisateurService.obtenirCompteUtilisateur(login);

	}

	@PatchMapping("/compte")
    public CompteUtilisateur modifierCompteUtilisateur(@RequestBody CompteUtilisateur compteUtilisateur){

    }

}
