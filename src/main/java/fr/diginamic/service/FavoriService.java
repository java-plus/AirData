/**
 * 
 */
package fr.diginamic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.diginamic.controller.dto.FavoriDtoPost;
import fr.diginamic.controller.dto.FavoriDtoPostAvecId;
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

	public FavoriService(FavoriRepository favoriRepository, UtilisateurRepository utilisateurRepository, CommuneRepository communeRepository) {
		this.favoriRepository = favoriRepository;
		this.utilisateurRepository = utilisateurRepository;
		this.communeRepository = communeRepository;
	}

	/**
	 * Méthode qui récupère tous les favoris de l'utilisateur qui est auhtentifié
	 * 
	 * @return List<FavoriSansUtilisateurDto>
	 */
	public List<FavoriSansUtilisateurDto> recupererFavoris() {
		TransformerFavori t = new TransformerFavori();
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
		List<Favori> listeFav = favoriRepository.findByUtilisateurId(utilisateur).orElseThrow(FavoriException::new);
		List<FavoriSansUtilisateurDto> listeFavSansDto = listeFav.stream().map((f) -> t.FavoriToFavoriDto(f)).collect(Collectors.toList());
		return listeFavSansDto;
	}

	/**
	 * Méthode qui enregistre en base le favori passé en param
	 * 
	 * @param favoriCreationDto
	 * @return
	 */
	public FavoriSansUtilisateurDto insererEnBase(FavoriDtoPost favoriCreationDto) {
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
		Commune commune = communeRepository.findByCodeCommune(favoriCreationDto.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);
		Favori favori = new Favori();
		favori.setCommune(commune);
		favori.setHumidity(favoriCreationDto.getHumidity());
		favori.setMesureCO(favoriCreationDto.getMesureCO());
		favori.setMesureNO2(favoriCreationDto.getMesureNO2());
		favori.setMesureO3(favoriCreationDto.getMesureO3());
		favori.setMesurePM10(favoriCreationDto.getMesurePM10());
		favori.setMesurePM25(favoriCreationDto.getMesurePM25());
		favori.setMesureSO2(favoriCreationDto.getMesureSO2());
		favori.setPressure(favoriCreationDto.getPressure());
		favori.setTemperature(favoriCreationDto.getTemperature());
		favori.setTempMax(favoriCreationDto.getTempMax());
		favori.setTempMin(favoriCreationDto.getTempMin());
		favori.setUtilisateur(utilisateur);
		favori.setWeatherDescription(favoriCreationDto.getWeatherDescription());
		favori.setWeatherIcon(favoriCreationDto.getWeatherIcon());
		favori.setWindDegrees(favoriCreationDto.getWindDegrees());
		favori.setWindSpeed(favoriCreationDto.getWindSpeed());

		Favori favoriSaved = favoriRepository.save(favori);

		TransformerFavori t = new TransformerFavori();

		FavoriSansUtilisateurDto favSansUtilisateurDto = t.FavoriToFavoriDto(favoriSaved);
		return favSansUtilisateurDto;

	}

	/**
	 * méthode qui supprime le favori
	 * 
	 * @param idFavori
	 */
	public void supprimerFavori(Integer idFavori) {
		favoriRepository.deleteById(idFavori);
	}

	/**
	 * Méthode qui modifie en base de donnée un favori
	 * 
	 * @param favoriCreationDtoAvecId un favori
	 * @return un favori sans l’utilisateur
	 */

	public FavoriSansUtilisateurDto modifier(FavoriDtoPostAvecId favoriCreationDtoAvecId) {
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);

		Commune commune = communeRepository.findByCodeCommune(favoriCreationDtoAvecId.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);

		Favori favori = new Favori();
		favori.setId(favoriCreationDtoAvecId.getId());
		favori.setCommune(commune);
		favori.setHumidity(favoriCreationDtoAvecId.getHumidity());
		favori.setMesureCO(favoriCreationDtoAvecId.getMesureCO());
		favori.setMesureNO2(favoriCreationDtoAvecId.getMesureNO2());
		favori.setMesureO3(favoriCreationDtoAvecId.getMesureO3());
		favori.setMesurePM10(favoriCreationDtoAvecId.getMesurePM10());
		favori.setMesurePM25(favoriCreationDtoAvecId.getMesurePM25());
		favori.setMesureSO2(favoriCreationDtoAvecId.getMesureSO2());
		favori.setPressure(favoriCreationDtoAvecId.getPressure());
		favori.setTemperature(favoriCreationDtoAvecId.getTemperature());
		favori.setTempMax(favoriCreationDtoAvecId.getTempMax());
		favori.setTempMin(favoriCreationDtoAvecId.getTempMin());
		favori.setUtilisateur(utilisateur);
		favori.setWeatherDescription(favoriCreationDtoAvecId.getWeatherDescription());
		favori.setWeatherIcon(favoriCreationDtoAvecId.getWeatherIcon());
		favori.setWindDegrees(favoriCreationDtoAvecId.getWindDegrees());
		favori.setWindSpeed(favoriCreationDtoAvecId.getWindSpeed());

		Favori favoriModifie = favoriRepository.save(favori);

		TransformerFavori t = new TransformerFavori();
		return t.FavoriToFavoriDto(favoriModifie);
	}
}