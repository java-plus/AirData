package fr.diginamic.controller;

import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.transformer.TransformerUtilisateur;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UtilisateurController {

    private TransformerUtilisateur transformerUtilisateur;

    public UtilisateurController(TransformerUtilisateur transformerUtilisateur) {
        this.transformerUtilisateur = transformerUtilisateur;
    }

    @PostMapping("/compte")
    public UtilisateurDto creerCompte(@Valid @RequestBody UtilisateurCreationComptePost utilisateurCreationComptePost){

        Utilisateur utilisateur = transformerUtilisateur.UtilisateurCreationComptePostToUtilisateur(utilisateurCreationComptePost);

        return transformerUtilisateur.UtilisateurToUtilisateurDto(utilisateur);

    }

}
