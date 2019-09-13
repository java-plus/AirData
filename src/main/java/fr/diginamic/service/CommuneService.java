package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.Commune;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;

@Service
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

	public Commune trouverCommuneParCode(String codeCommune) {
		return communeRepository.findByCodeCommune(codeCommune).orElseThrow(CommuneNonTrouveeException::new);
	}

	public List<Commune> obtenirLaListeDesCommunes() {
		return communeRepository.findAll();
	}

	public void insererEnBas(List<Commune> listeDesCommunes) {
		communeRepository.saveAll(listeDesCommunes);
	}

}
