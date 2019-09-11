package fr.diginamic.service;

import fr.diginamic.entites.Utilisateur;
import fr.diginamic.repository.UtilisateurRepository;

import javax.rmi.CORBA.Util;

public class UtilisateurService {

    UtilisateurRepository utilisateurRepository;


    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public void creerUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }
}
