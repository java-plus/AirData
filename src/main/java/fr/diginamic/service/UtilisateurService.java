package fr.diginamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	public CompteUtilisateur obtenirCompteUtilisateur(String login) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findCompteUtilisateurWithIdentifiant(login);
	}

}
