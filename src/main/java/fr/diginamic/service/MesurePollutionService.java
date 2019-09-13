package fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesurePollution;
import fr.diginamic.repository.MesurePollutionRepository;

@Service
public class MesurePollutionService {

	MesurePollutionRepository mesurePollutionRepository;

	/**
	 * Constructeur
	 * 
	 * @param mesurePollutionRepository
	 */
	public MesurePollutionService(MesurePollutionRepository mesurePollutionRepository) {
		super();
		this.mesurePollutionRepository = mesurePollutionRepository;

	}

	public void insererEnBase(List<MesurePollution> ListeDeMesures) {
		// ListeDeMesures.removeAll(Collections.singleton(null));
		mesurePollutionRepository.saveAll(ListeDeMesures);
	}

	public List<MesurePollution> obtenirLesMesuresDePollution(String codeCommune) {
		// TODO retrouver la commune de l'utilisateur via
		return mesurePollutionRepository.obtenirLesMesuresDePollution(codeCommune);
	}

	public Optional<MesurePollution> obtenirMesurePollution(MesurePollution mesurePollution) {
		// TODO Auto-generated method stub

		return mesurePollutionRepository.findById(mesurePollution.getId());
	}

	public void mettreEnBaseMesurePollution(MesurePollution mesurePollution) {
		// TODO Auto-generated method stub
		mesurePollutionRepository.save(mesurePollution);
	}

}
