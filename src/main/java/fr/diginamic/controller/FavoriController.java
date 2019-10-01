package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping
	public FavoriSansUtilisateurDto enregistrerFavori(@RequestBody FavoriDtoPost favoriCreationDto) {

		return favoriService.insererEnBase(favoriCreationDto);

	}

	@DeleteMapping
	public void supprimerFavori(@RequestBody String id) {
		favoriService.supprimerFavori(id);
	}

	@PatchMapping
	public FavoriSansUtilisateurDto modifierFavori(@RequestBody FavoriDtoPostAvecId favoriCreationDtoAvecId) {

		return favoriService.modifier(favoriCreationDtoAvecId);

	}

}
