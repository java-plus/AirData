package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.Commune;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;

/**
 * class CommuneService cette classe fait principalement des appels et des
 * insertions en base grâce au CommuneRepository Elle est principalement
 * utilisée par des controlleurs
 * 
 * @author Diginamic02
 *
 */@Service
public class CommuneService {

	private CommuneRepository communeRepository;

	/**
	 * Constructor
	 * 
	 * @param communeRepository
	 */
	public CommuneService(CommuneRepository communeRepository) {
		this.communeRepository = communeRepository;
	}

	/**
	 * Methode trouverCommuneParCode(String codeCommune) retourne un Commune
	 * Cette methode sert à sortir de la base de donnée un objet
	 * <optionnal>Commune Commune dont le codeCommune correspond
	 * 
	 * @param codeCommune
	 * @return
	 */
	public Commune trouverCommuneParCode(String codeCommune) {
		return communeRepository.findByCodeCommune(codeCommune).orElseThrow(CommuneNonTrouveeException::new);
	}

	/**
	 * Methode List<Commune> obtenirLaListeDesCommunes() Cette methode permet de
	 * sortir d ela base de donnée la liste complète des Communes qui la
	 * compose.
	 * 
	 * @return
	 */
	public List<Commune> obtenirLaListeDesCommunes() {
		return communeRepository.findAll();
	}

	/**
	 * Methode void insererEnBas(List<Commune> listeDesCommunes) Cette methode
	 * permet d'inserer en base de donnée une liste d'objets Communes
	 * 
	 * @param listeDesCommunes
	 */
	public void insererEnBas(List<Commune> listeDesCommunes) {
		communeRepository.saveAll(listeDesCommunes);
	}

}
