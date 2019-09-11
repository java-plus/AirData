package fr.diginamic.service;

import fr.diginamic.entites.Utilisateur;
import fr.diginamic.exception.UtilisateurIncorrectException;
import fr.diginamic.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.CompteUtilisateur;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Service
public class UtilisateurService {

    UtilisateurRepository utilisateurRepository;
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();


    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public void creerUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }

	public CompteUtilisateur obtenirCompteUtilisateur(String login) {

		return utilisateurRepository.findCompteUtilisateurWithIdentifiant(login);
	}

    public void insererEnBase(Utilisateur utilisateur) {
        if(validator.validate(utilisateur).isEmpty()){
            utilisateurRepository.save(utilisateur);
        }else{
            throw new UtilisateurIncorrectException();
        }
    }
}
