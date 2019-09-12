/**
 * 
 */
package fr.diginamic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.Favori;
import fr.diginamic.entites.Utilisateur;
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
		String utilisateurId = utilisateurRepository.findIdWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurId);
		return favoriRepository.findByUtilisateurId(utilisateur);

	}

	public Favori insererEnBase(Favori favoriAEnregistrer) {

		String utilisateurId = utilisateurRepository.findIdWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurId);
		favoriAEnregistrer.setUtilisateur(utilisateur);

		return favoriRepository.save(favoriAEnregistrer);

	}

	public void supprimerFavori(Favori favoriASupprimer) {
		favoriRepository.delete(favoriASupprimer);
	}
}