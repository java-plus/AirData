package fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.repository.MesureMeteoRepository;

@Service
public class MesureMeteoService {

	MesureMeteoRepository mesureMeteoRepository;

	/**
	 * Constructeur
	 * 
	 * @param mesureMeteoRepository
	 */
	public MesureMeteoService(MesureMeteoRepository mesureMeteoRepository) {
		super();
		this.mesureMeteoRepository = mesureMeteoRepository;
	}

	public void insererEnBase(List<MesureMeteo> ListeDeMesures) {
		// ListeDeMesures.removeAll(Collections.singleton(null));
		mesureMeteoRepository.saveAll(ListeDeMesures);
	}

	public List<MesureMeteo> obtenirLesMesuresDeMeteo(String codeCommune) {
		// TODO Auto-generated method stub
		return mesureMeteoRepository.obtenirLesMesuresDeMeteo(codeCommune);
	}

	public Optional<MesureMeteo> obtenirMesureMeteo(MesureMeteo mesureMeteo) {
		// TODO Auto-generated method stub
		return mesureMeteoRepository.findById(mesureMeteo.getId());
	}

	public void mettreEnBaseMesureMeteo(MesureMeteo mesureMeteo) {
		// TODO Auto-generated method stub
		mesureMeteoRepository.save(mesureMeteo);
	}

}
