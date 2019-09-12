package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.CommuneMesuresDto;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;

@Service
public class CommuneService {

	private CommuneRepository communeRepository;
	private MesureMeteoService mesureMeteoService;
	private MesurePollutionService mesurePollutionService;

	/**
	 * Constructor
	 * 
	 * @param communeRepository
	 * @param mesureMeteoService
	 * @param mesurePollutionService
	 */
	public CommuneService(CommuneRepository communeRepository, MesureMeteoService mesureMeteoService, MesurePollutionService mesurePollutionService) {
		this.communeRepository = communeRepository;
		this.mesureMeteoService = mesureMeteoService;
		this.mesurePollutionService = mesurePollutionService;
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

	public CommuneMesuresDto recupererMesureParCommune(String codeCommune) {
		Commune commune = communeRepository.findByCodeCommune(codeCommune).orElseThrow(CommuneNonTrouveeException::new);
		Integer population = commune.getPopulation();
		List<MesureMeteo> listeMeteo = mesureMeteoService.obtenirLesMesuresDeMeteo(codeCommune);
		List<MesurePollution> listePollution = mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);
		return null;
	}

}
