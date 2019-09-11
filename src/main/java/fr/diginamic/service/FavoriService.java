/**
 * 
 */
package fr.diginamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.Favori;
import fr.diginamic.repository.FavoriRepository;

@Service
public class FavoriService {

	@Autowired
	private FavoriRepository favoriRepository;

	public List<Favori> recupererFavoris(Integer utilisateurId) {

		return favoriRepository.recupererFavorisSelonUtilisateur(utilisateurId);

	}

	public void insererEnBase() {

	}

}
