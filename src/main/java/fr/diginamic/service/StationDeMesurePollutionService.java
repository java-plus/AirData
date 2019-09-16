package fr.diginamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.StationDeMesurePollution;
import fr.diginamic.repository.StationPollutionRepository;

@Service
public class StationDeMesurePollutionService {

	@Autowired
	StationPollutionRepository stationPollutionRepository;

	public void insererEnBaseListeStationsDeMesurePollution(
			List<StationDeMesurePollution> listeDeStationDeMesurePollution) {
		stationPollutionRepository.saveAll(listeDeStationDeMesurePollution);
	}
}
