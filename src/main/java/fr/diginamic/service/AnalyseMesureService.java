/**
 * 
 */
package fr.diginamic.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.controller.dto.AnalyseMesureDtoPost;
import fr.diginamic.controller.dto.AnalyseMesurePollutionDto;
import fr.diginamic.controller.dto.MesureDto;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;
import fr.diginamic.repository.MesureMeteoRepository;
import fr.diginamic.repository.MesurePollutionRepository;
import fr.diginamic.transformer.TransformerAnalyseMesure;

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

	public AnalyseMesureDto recupererHistoriqueIndicateur(AnalyseMesureDtoPost analyseMesureDtoPost) {
		Commune commune = communeRepository.findByCodeCommune(analyseMesureDtoPost.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);

		Optional<List<MesureMeteo>> listeIndicateurMeteo = null;
		List<MesurePollution> listeindicateurPollution = null;
		List<AnalyseMesurePollutionDto> analyseMesurePollutionDto = null;

		TransformerAnalyseMesure transformerAnalyseMesure = new TransformerAnalyseMesure();

		if (analyseMesureDtoPost.getIndicateur().equals("meteo")) {
			listeIndicateurMeteo = mesureMeteoRepository.obtenirTousLesIndicateursParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("temperature")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesTemperatureParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("pressure")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesPressureParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("humidity")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesHumidityParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("tempMin")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesTempsMinParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("tempMax")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesTempsMaxParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("windSpeed")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesWindSpeedParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());

		} else if (analyseMesureDtoPost.getIndicateur().equals("windDegrees")) {

			listeIndicateurMeteo = mesureMeteoRepository.obtenirLesWindDegreesParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
		}

		else if (analyseMesureDtoPost.getIndicateur().equals("O3")) {
			listeindicateurPollution = mesurePollutionRepository.obtenirLesO3ParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
			System.out.println(listeindicateurPollution);
		}
		// else if (analyseMesureDtoPost.getIndicateur().equals("PM10")) {
		//
		// listeindicateurPollution = mesurePollutionRepository.obtenirLesPM10ParPeriode(analyseMesureDtoPost.getCodeCommune(),
		// analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
		//
		// } else if (analyseMesureDtoPost.getIndicateur().equals("PM25")) {
		//
		// listeindicateurPollution = mesurePollutionRepository.obtenirLesPM25ParPeriode(analyseMesureDtoPost.getCodeCommune(),
		// analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
		//
		// } else if (analyseMesureDtoPost.getIndicateur().equals("NO2")) {
		//
		// listeindicateurPollution = mesurePollutionRepository.obtenirLesNO2ParPeriode(analyseMesureDtoPost.getCodeCommune(),
		// analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
		//
		// } else if (analyseMesureDtoPost.getIndicateur().equals("SO2")) {
		//
		// listeindicateurPollution = mesurePollutionRepository.obtenirLesS02ParPeriode(analyseMesureDtoPost.getCodeCommune(),
		// analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
		//
		// } else if (analyseMesureDtoPost.getIndicateur().equals("CO")) {
		//
		// listeindicateurPollution = mesurePollutionRepository.obtenirLesCOParPeriode(analyseMesureDtoPost.getCodeCommune(),
		// analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin());
		//
		// }

		// TODO transformer les valeurs qui ne sont pas en Double, vers du Double, pour pollution

		AnalyseMesureDto analyseMesureDto = new AnalyseMesureDto();
		analyseMesureDto.setNom(commune.getNom());
		analyseMesureDto.setPopulation(commune.getPopulation());
		analyseMesureDto.setIndicateur(analyseMesureDtoPost.getIndicateur());
		analyseMesureDto.setDateDebut(analyseMesureDtoPost.getDateDebut());
		analyseMesureDto.setDateFin(analyseMesureDto.getDateFin());

		if (listeindicateurPollution != null) {
			List<MesureDto> listeMesureDto = new ArrayList<MesureDto>();

			for (int i = 0; i < listeindicateurPollution.size(); i++) {
				MesureDto mesureDto = new MesureDto();
				ZonedDateTime date = listeindicateurPollution.get(i).getDate();
				Double valeur = listeindicateurPollution.get(i).getValeur();
				mesureDto.setDate(date);
				mesureDto.setValeur(valeur);
				listeMesureDto.add(mesureDto);
			}

		}

		// analyseMesureDto.setDonnees();
		return analyseMesureDto;

	}

}
