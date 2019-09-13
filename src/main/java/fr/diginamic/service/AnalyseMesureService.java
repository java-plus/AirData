/**
 * 
 */
package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.controller.dto.IndicateurAvecDateEtValeurDto;
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

		List<IndicateurAvecDateEtValeurDto> listeIndicateur;

		if (analyseMesureDto.getIndicateur().equals("temperature")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesTemperatureParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("pressure")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesPressureParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("humidity")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesHumidityParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("tempMin")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesTempsMinParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("tempMax")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesTempsMaxParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("windSpeed")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesWindSpeedParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		} else if (analyseMesureDto.getIndicateur().equals("windDegrees")) {
			listeIndicateur = mesureMeteoRepository.obtenirLesWindDegreesParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(), analyseMesureDto.getDateFin());
		}
		//
		// else if (analyseMesureDto.getIndicateur().equals("O3")) {
		// mesurePollutionRepository.obtenirLesO3ParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(),
		// analyseMesureDto.getDateFin());
		// } else if (analyseMesureDto.getIndicateur().equals("PM10")) {
		// mesurePollutionRepository.obtenirLesPM10ParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(),
		// analyseMesureDto.getDateFin());
		// } else if (analyseMesureDto.getIndicateur().equals("PM25")) {
		// mesurePollutionRepository.obtenirLesPM25ParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(),
		// analyseMesureDto.getDateFin());
		// } else if (analyseMesureDto.getIndicateur().equals("NO2")) {
		// mesurePollutionRepository.obtenirLesNO2ParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(),
		// analyseMesureDto.getDateFin());
		// } else if (analyseMesureDto.getIndicateur().equals("SO2")) {
		// mesurePollutionRepository.obtenirLesS02ParPeriode(analyseMesureDto.getCodeCommune(), analyseMesureDto.getDateDebut(),
		// analyseMesureDto.getDateFin());
		// }

		return analyseMesureDto;

	}

}
