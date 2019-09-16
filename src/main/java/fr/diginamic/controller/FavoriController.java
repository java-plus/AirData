package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Favori;
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
	public List<Favori> recupererFavoris() {
		return favoriService.recupererFavoris();

	}

	// @PostMapping
	// public Favori enregistrerFavori(@RequestBody FavoriDtoPost favoriCreationDto) {
	//
	// return favoriService.insererEnBase(favoriCreationDto);
	//
	// }
	//
	// @DeleteMapping
	// public void supprimerFavori(@RequestBody Favori favori) {
	// // FavoriService.supprimerFavori(Favori);
	// }
	//
	// @PatchMapping
	// public Favori modifierFavori() {
	//
	// return favoriModifie;
	// }

}
