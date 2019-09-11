package fr.diginamic.service;

import fr.diginamic.entites.Utilisateur;
import fr.diginamic.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.CompteUtilisateur;

@Service
public class UtilisateurService {

    UtilisateurRepository utilisateurRepository;


    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public void creerUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }

	public CompteUtilisateur obtenirCompteUtilisateur(String login) {

		return utilisateurRepository.findCompteUtilisateurWithIdentifiant(login);
	}

}
