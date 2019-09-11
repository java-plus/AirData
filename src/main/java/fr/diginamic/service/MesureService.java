package fr.diginamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesurePollution;
import fr.diginamic.repository.MesureRepository;

@Service
public class MesureService {

	@Autowired
	MesureRepository mesureRepository;

	public void insererEnBase(List<MesurePollution> ListeDeMesures) {
		// ListeDeMesures.removeAll(Collections.singleton(null));
		mesureRepository.saveAll(ListeDeMesures);
	}

}
