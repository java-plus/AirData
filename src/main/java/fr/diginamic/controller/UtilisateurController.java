package fr.diginamic.controller;

import fr.diginamic.controller.dto.UtilisateurConnecteService;
import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.controller.dto.UtilisateurRgpdDto;
import fr.diginamic.entites.Role;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.exception.CookieIntrouvableException;
import fr.diginamic.transformer.TransformerUtilisateur;
import fr.diginamic.utils.UtilisateurConnecteUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.service.UtilisateurService;

import java.util.Arrays;

@RestController
public class UtilisateurController {

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

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
    public ResponseEntity<?> supprimerCompte(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        String identifiant =UtilisateurConnecteUtils.recupererIdentifiant();
        utilisateurService.supprimerCompte(identifiant);
        Cookie cookie = Arrays.stream(httpServletRequest.getCookies()).filter(c -> c.getName().equals(TOKEN_COOKIE)).findFirst().orElseThrow(CookieIntrouvableException::new);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok().build();
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
