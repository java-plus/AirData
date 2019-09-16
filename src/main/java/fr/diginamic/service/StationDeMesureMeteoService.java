package fr.diginamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.StationDeMesureMeteo;
import fr.diginamic.repository.StationMeteoRepository;

@Service
public class StationDeMesureMeteoService {

	@Autowired
	StationMeteoRepository stationMeteoRepository;

	public void insererEnBaseListeStationsDeMesureMeteo(List<StationDeMesureMeteo> listeDeStationDeMesureMeteo) {
		stationMeteoRepository.saveAll(listeDeStationDeMesureMeteo);
	}

}
