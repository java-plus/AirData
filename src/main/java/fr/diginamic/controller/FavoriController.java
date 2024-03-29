package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.controller.dto.FavoriDtoPost;
import fr.diginamic.controller.dto.FavoriDtoPostAvecId;
import fr.diginamic.controller.dto.FavoriSansUtilisateurDto;
import fr.diginamic.service.FavoriService;

/**
 * Class Controller interceptant les requètes sur "/favoris"
 * 
 * @author Eloi
 *
 */
@RestController
@RequestMapping("/favoris")
public class FavoriController {

	@Autowired
	private FavoriService favoriService;

	/**
	 * Méthode qui récupère la liste des favoris de l'utilisateur qui est connecté
	 * 
	 * @return List<Favori> La liste des favoris de l'ulisateur connecté
	 */
	@GetMapping
	public List<FavoriSansUtilisateurDto> recupererFavoris() {
		return favoriService.recupererFavoris();

	}

	/**
	 * Méthode qui enregistre en base de donnée le favori créé dans le font
	 * 
	 * @param favoriCreationDto
	 *            un FavoriDtoPost (= un Favori sans l'id et sans l'utilisateur)
	 * @return un FavoriSansUtilisateurDto
	 */
	@PostMapping
	public FavoriSansUtilisateurDto enregistrerFavori(@RequestBody FavoriDtoPost favoriCreationDto) {
		return favoriService.insererEnBase(favoriCreationDto);
	}

	/**
	 * Méthode qui supprime de la base de donnée le favori correspondant à l'id passé en param
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}")
	public void supprimerFavori(@PathVariable Integer id) {
		favoriService.supprimerFavori(id);
	}

	/**
	 * 
	 * Méthode qui enregistre les modifications d'un favori faites dans le front, en base de donnée
	 * 
	 * @param favoriCreationDtoAvecId
	 * @return
	 */
	@PatchMapping
	public FavoriSansUtilisateurDto modifierFavori(@RequestBody FavoriDtoPostAvecId favoriCreationDtoAvecId) {
		return favoriService.modifier(favoriCreationDtoAvecId);

	}

}
