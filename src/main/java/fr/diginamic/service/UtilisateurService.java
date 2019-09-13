package fr.diginamic.service;

import fr.diginamic.controller.dto.UtilisateurConnecteService;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.exception.UtilisateurIncorrectException;
import fr.diginamic.exception.UtilisateurNonTrouveException;
import fr.diginamic.repository.CompteUtilisateurRepository;
import fr.diginamic.repository.UtilisateurRepository;

import fr.diginamic.utils.UtilisateurConnecteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.CompteUtilisateur;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class UtilisateurService {

    UtilisateurRepository utilisateurRepository;
    CompteUtilisateurRepository compteUtilisateurRepository;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    public UtilisateurService(UtilisateurRepository utilisateurRepository, CompteUtilisateurRepository compteUtilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.compteUtilisateurRepository = compteUtilisateurRepository;
    }

    public void creerUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }

	public CompteUtilisateur obtenirCompteUtilisateur() {
        String identifiant = UtilisateurConnecteUtils.recupererIdentifiant();
		return utilisateurRepository.findCompteUtilisateurWithIdentifiant(identifiant);
	}

    public void insererEnBase(Utilisateur utilisateur) {
        if(validator.validate(utilisateur).isEmpty()){
            utilisateurRepository.save(utilisateur);
        }else{
            throw new UtilisateurIncorrectException();
        }
    }

    public CompteUtilisateur modifierCompteUtilisateur(CompteUtilisateur modification){

        CompteUtilisateur compteUtilisateur = utilisateurRepository.findCompteUtilisateurWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());
        if(modification.getNotificationMeteo()!=null){
            compteUtilisateur.setNotificationMeteo(modification.getNotificationMeteo());
        }
        if(modification.getNotificationPollution()!=null){
            compteUtilisateur.setNotificationPollution(modification.getNotificationPollution());
        }
        compteUtilisateurRepository.save(compteUtilisateur);
        return compteUtilisateur;

    }

    public ResponseEntity<?> supprimerCompte(String identifiant){
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant).orElseThrow(UtilisateurNonTrouveException::new);
        utilisateurRepository.delete(utilisateur);
        return ResponseEntity.ok().build();
    }

    public Utilisateur recupererUtilisateur() {
       return utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
    }
}
