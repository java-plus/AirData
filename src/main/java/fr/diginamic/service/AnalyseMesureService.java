/**
 * 
 */
package fr.diginamic.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.controller.dto.AnalyseMesureDtoPost;
import fr.diginamic.controller.dto.MesureDto;
import fr.diginamic.entites.Commune;
import fr.diginamic.exception.AnalyseMesureException;
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

	/**
	 * @param analyseMesureDtoPost
	 * @return
	 */
	public AnalyseMesureDto recupererHistoriqueIndicateur(AnalyseMesureDtoPost analyseMesureDtoPost) {
		Commune commune = communeRepository.findByCodeCommune(analyseMesureDtoPost.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);

		List<Object[]> listeindicateurs = null;

		if (analyseMesureDtoPost.getIndicateur().equals("temperature")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesTemperatureParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("pressure")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesPressureParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("humidity")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesHumidityParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("tempMin")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesTempsMinParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("tempMax")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesTempsMaxParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("windSpeed")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesWindSpeedParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("windDegrees")) {

			listeindicateurs = mesureMeteoRepository.obtenirLesWindDegreesParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("O3")) {
			listeindicateurs = mesurePollutionRepository.obtenirLesO3ParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("PM10")) {

			listeindicateurs = mesurePollutionRepository.obtenirLesPM10ParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("PM25")) {

			listeindicateurs = mesurePollutionRepository.obtenirLesPM25ParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("NO2")) {

			listeindicateurs = mesurePollutionRepository.obtenirLesNO2ParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("SO2")) {

			listeindicateurs = mesurePollutionRepository.obtenirLesS02ParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else if (analyseMesureDtoPost.getIndicateur().equals("CO")) {

			listeindicateurs = mesurePollutionRepository.obtenirLesCOParPeriode(analyseMesureDtoPost.getCodeCommune(), analyseMesureDtoPost.getDateDebut(), analyseMesureDtoPost.getDateFin()).orElseThrow(AnalyseMesureException::new);

		} else {
			throw new AnalyseMesureException("Indicateur (" + analyseMesureDtoPost.getIndicateur() + ") inconnu");

		}

		AnalyseMesureDto analyseMesureDto = new AnalyseMesureDto();
		analyseMesureDto.setNom(commune.getNom());
		analyseMesureDto.setPopulation(commune.getPopulation());
		analyseMesureDto.setIndicateur(analyseMesureDtoPost.getIndicateur());
		analyseMesureDto.setDateDebut(analyseMesureDtoPost.getDateDebut());
		analyseMesureDto.setDateFin(analyseMesureDtoPost.getDateFin());

		List<MesureDto> listeMesureDto = new ArrayList<>();

		for (int i = 0; i < listeindicateurs.size(); i++) {
			MesureDto mesureDto = new MesureDto();
			Object[] obj = listeindicateurs.get(i);

			ZonedDateTime date = (ZonedDateTime) obj[1];

			Integer monInt;
			Double valeur;

			if (obj[0] instanceof Integer) {
				monInt = (Integer) obj[0];
				valeur = monInt.doubleValue();
			} else {
				valeur = (Double) obj[0];
			}

			mesureDto.setDate(date);
			mesureDto.setValeur(valeur);
			listeMesureDto.add(mesureDto);
		}

		analyseMesureDto.setDonnees(listeMesureDto);

		return analyseMesureDto;

	}

}
