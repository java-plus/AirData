package fr.diginamic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Favori;
import fr.diginamic.service.FavoriService;

@RestController
@RequestMapping("/favoris")
public class FavoriController {

	private FavoriService FavoriService;

	@GetMapping
	public List<Favori> recupererFavoris() {

		return FavoriService.recupererFavoris(utilisateurId);
		return null;

	}

	@PostMapping
	public List<Favori> enregistrerFavori(@RequestBody Favori favori) {
		FavoriService.insererEnBase(favori);
		return null;

	}

	@DeleteMapping
	public void supprimerFavori() {
		FavoriService.supprimerFavori(Favori);
	}

}
