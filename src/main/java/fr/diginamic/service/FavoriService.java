/**
 * 
 */
package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.Favori;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.exception.FavoriException;
import fr.diginamic.exception.UtilisateurNonTrouveException;
import fr.diginamic.repository.FavoriRepository;
import fr.diginamic.repository.UtilisateurRepository;
import fr.diginamic.utils.UtilisateurConnecteUtils;

@Service
public class FavoriService {

	private FavoriRepository favoriRepository;
	private UtilisateurRepository utilisateurRepository;

	public FavoriService(FavoriRepository favoriRepository, UtilisateurRepository utilisateurRepository) {
		this.favoriRepository = favoriRepository;
		this.utilisateurRepository = utilisateurRepository;
	}

	public List<Favori> recupererFavoris() {
		// String utilisateurId = utilisateurRepository.findIdWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
		return favoriRepository.findByUtilisateurId(utilisateur).orElseThrow(FavoriException::new);

	}

	// public Favori insererEnBase(FavoriDtoPost favoriCreationDto) {
	//
	// String utilisateurId = utilisateurRepository.findIdWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());
	//
	// Utilisateur utilisateur = new Utilisateur();
	// utilisateur.setId(utilisateurId);
	// favoriCreationDto.setUtilisateur(utilisateur);
	//
	// return favoriRepository.save(favoriCreationDto);
	//
	// }

	public void supprimerFavori(Integer idFavori) {
		favoriRepository.deleteById(idFavori);
	}
}