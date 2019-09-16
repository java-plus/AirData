package fr.diginamic.service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.exception.UtilisateurIncorrectException;
import fr.diginamic.exception.UtilisateurNonTrouveException;
import fr.diginamic.repository.CompteUtilisateurRepository;
import fr.diginamic.repository.UtilisateurRepository;
import fr.diginamic.utils.UtilisateurConnecteUtils;

/**
 * Cette classe représente un Service gérant les utilisateurs
 * 
 * @author Diginamic02
 *
 */
@Service
public class UtilisateurService {

	UtilisateurRepository utilisateurRepository;
	CompteUtilisateurRepository compteUtilisateurRepository;
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public UtilisateurService(UtilisateurRepository utilisateurRepository,
			CompteUtilisateurRepository compteUtilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
		this.compteUtilisateurRepository = compteUtilisateurRepository;
	}

	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);
	}

	/**
	 * Cette methode retourne le compte utilisateur en BDD de l'utilisateur
	 * courant
	 * 
	 * @return
	 */
	public CompteUtilisateur obtenirCompteUtilisateur() {
		String identifiant = UtilisateurConnecteUtils.recupererIdentifiant();
		return utilisateurRepository.findCompteUtilisateurWithIdentifiant(identifiant);
	}

	/**
	 * Cette methode insère dans la base de donnée un objet Utilisateur
	 * 
	 * @param utilisateur
	 */
	public void insererEnBase(Utilisateur utilisateur) {
		if (validator.validate(utilisateur).isEmpty()) {
			utilisateurRepository.save(utilisateur);
		} else {
			throw new UtilisateurIncorrectException();
		}
	}

	public CompteUtilisateur modifierCompteUtilisateur(CompteUtilisateur modification) {

		CompteUtilisateur compteUtilisateur = utilisateurRepository
				.findCompteUtilisateurWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());
		if (modification.getNotificationMeteo() != null) {
			compteUtilisateur.setNotificationMeteo(modification.getNotificationMeteo());
		}
		if (modification.getNotificationPollution() != null) {
			compteUtilisateur.setNotificationPollution(modification.getNotificationPollution());
		}
		compteUtilisateurRepository.save(compteUtilisateur);
		return compteUtilisateur;

	}

	public ResponseEntity<?> supprimerCompte(String identifiant) {
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant)
				.orElseThrow(UtilisateurNonTrouveException::new);
		utilisateurRepository.delete(utilisateur);
		return ResponseEntity.ok().build();
	}

	public Utilisateur recupererUtilisateur() {
		return utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant())
				.orElseThrow(UtilisateurNonTrouveException::new);
	}
}
