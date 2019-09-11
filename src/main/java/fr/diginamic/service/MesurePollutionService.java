package fr.diginamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesurePollution;
import fr.diginamic.repository.MesurePollutionRepository;

@Service
public class MesurePollutionService {
	@Autowired
	MesurePollutionRepository mesurePollutionRepository;

	public List<MesurePollution> obtenirLesMesuresDePollution(String codeCommune) {
		// TODO retrouver la commune de l'utilisateur via
		return mesurePollutionRepository.obtenirLesMesuresDePollution(codeCommune);
	}

}
