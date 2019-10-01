/**
 * 
 */
package fr.diginamic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.FavoriDtoPost;
import fr.diginamic.controller.dto.FavoriSansUtilisateurDto;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.Favori;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.exception.FavoriException;
import fr.diginamic.exception.UtilisateurNonTrouveException;
import fr.diginamic.repository.CommuneRepository;
import fr.diginamic.repository.FavoriRepository;
import fr.diginamic.repository.UtilisateurRepository;
import fr.diginamic.transformer.TransformerFavori;
import fr.diginamic.utils.UtilisateurConnecteUtils;

@Service
public class FavoriService {

	private FavoriRepository favoriRepository;
	private UtilisateurRepository utilisateurRepository;
	private CommuneRepository communeRepository;

	public FavoriService(FavoriRepository favoriRepository, UtilisateurRepository utilisateurRepository) {
		this.favoriRepository = favoriRepository;
		this.utilisateurRepository = utilisateurRepository;
	}

	public List<FavoriSansUtilisateurDto> recupererFavoris() {

		TransformerFavori t = new TransformerFavori();
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
		List<Favori> listeFav = favoriRepository.findByUtilisateurId(utilisateur).orElseThrow(FavoriException::new);
		List<FavoriSansUtilisateurDto> listeFavSansDto = listeFav.stream().map((f) -> t.FavoriToFavoriDto(f)).collect(Collectors.toList());
		return listeFavSansDto;

	}

	public Favori insererEnBase(FavoriDtoPost favoriCreationDto) {
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
		Commune commune = communeRepository.findByCodeCommune(favoriCreationDto.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);

		// String utilisateurId = utilisateurRepository.findIdWithIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant());
		//
		// Utilisateur utilisateur = new Utilisateur();
		// utilisateur.setId(utilisateurId);
		//
		// favoriCreationDto.setUtilisateur(utilisateur);

		Favori favori = new Favori(commune, utilisateur, favoriCreationDto.getWeatherDescription(), favoriCreationDto.getWeatherIcon(), favoriCreationDto.getTemperature(), favoriCreationDto.getPressure(), favoriCreationDto.getHumidity(), favoriCreationDto.getTempMin(), favoriCreationDto.getTempMax(), favoriCreationDto.getWindSpeed(), favoriCreationDto.getWindDegrees(),
				favoriCreationDto.getMesureSO2(), favoriCreationDto.getMesurePM25(), favoriCreationDto.getMesurePM10(), favoriCreationDto.getMesureO3(), favoriCreationDto.getMesureNO2(), favoriCreationDto.getMesureCO());

		return favoriRepository.save(favori);

	}

	public void supprimerFavori(Integer idFavori) {
		favoriRepository.deleteById(idFavori);
	}
}