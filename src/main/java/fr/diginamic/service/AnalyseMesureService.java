/**
 * 
 */
package fr.diginamic.service;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.entites.Commune;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;
import fr.diginamic.repository.MesureMeteoRepository;
import fr.diginamic.repository.MesurePollutionRepository;

/**
 * @author Eloi
 *
 */
@Service
public class AnalyseMesureService {

	private CommuneRepository communeRepository;
	private MesureMeteoRepository mesureMeteoRepository;
	private MesurePollutionRepository mesurePollutionRepository;

	/**
	 * Constructor
	 * 
	 * @param communeRepository
	 * @param mesureMeteoRepository
	 * @param mesurePollutionRepository
	 */
	public AnalyseMesureService(CommuneRepository communeRepository, MesureMeteoRepository mesureMeteoRepository, MesurePollutionRepository mesurePollutionRepository) {
		this.communeRepository = communeRepository;
		this.mesureMeteoRepository = mesureMeteoRepository;
		this.mesurePollutionRepository = mesurePollutionRepository;
	}

	public AnalyseMesureDto recupererHistoriqueIndicateur(AnalyseMesureDto analyseMesureDto) {
		Commune commune = communeRepository.findByCodeCommune(analyseMesureDto.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);
		analyseMesureDto.setNom(commune.getNom());
		analyseMesureDto.setPopulation(commune.getPopulation());

		// appel de la requete en bdd en fonction de l'indicateur demandé

		if (analyseMesureDto.getIndicateur().equals("temperature")) {
			mesureMeteoRepository.obtenirLesTemperatureParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("pressure")) {
			mesureMeteoRepository.obtenirLesPressureParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("humidity")) {
			mesureMeteoRepository.obtenirLesHumidityParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("tempMin")) {
			mesureMeteoRepository.obtenirLesTempsMinParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("tempMax")) {
			mesureMeteoRepository.obtenirLesTempsMaxParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("windSpeed")) {
			mesureMeteoRepository.obtenirLesWindSpeedParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("windDegrees")) {
			mesureMeteoRepository.obtenirLesWindDegreesParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		}

		else if (analyseMesureDto.getIndicateur().equals("temperature")) {

		}

		return analyseMesureDto;

	}

}
