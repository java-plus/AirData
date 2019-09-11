package fr.diginamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.Commune;
import fr.diginamic.repository.CommuneRepository;

@Service
public class CommuneService {

	@Autowired
	CommuneRepository communeRepository;

	public List<Commune> obtenirLaListeDesCommunes() {
		// TODO Auto-generated method stub
		return communeRepository.findAll();
	}

	public void insererEnBas(List<Commune> listeDesCommunes) {
		// TODO Auto-generated method stub
		communeRepository.saveAll(listeDesCommunes);
	}

}
