/**
 * 
 */
package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.CommuneMesuresDto;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;

/**
 * @author Eloi
 *
 */
@Service
public class CommuneMesuresService {

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
	public CommuneMesuresService(CommuneRepository communeRepository, MesureMeteoService mesureMeteoService, MesurePollutionService mesurePollutionService) {
		this.communeRepository = communeRepository;
		this.mesureMeteoService = mesureMeteoService;
		this.mesurePollutionService = mesurePollutionService;
	}

	public CommuneMesuresDto recupererMesureParCommune(String codeCommune) {
		Commune commune = communeRepository.findByCodeCommune(codeCommune).orElseThrow(CommuneNonTrouveeException::new);

		List<MesureMeteo> listeMeteo = mesureMeteoService.obtenirLesMesuresDeMeteo(codeCommune);
		List<MesurePollution> listePollution = mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);

		CommuneMesuresDto communeMesuresDto = new CommuneMesuresDto(listeMeteo, listePollution, commune.getPopulation());

		return communeMesuresDto;
	}

}
