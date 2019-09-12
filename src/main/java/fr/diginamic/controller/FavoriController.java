package fr.diginamic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.FavoriDtoPost;
import fr.diginamic.entites.Favori;
import fr.diginamic.service.FavoriService;
import fr.diginamic.transformer.TransformerFavori;

@RestController
@RequestMapping("/favoris")
public class FavoriController {

	private FavoriService FavoriService;

	private TransformerFavori transformerFavori;

	@GetMapping
	public List<Favori> recupererFavoris() {

		// return FavoriService.recupererFavoris(utilisateurId);
		return null;

	}

	@PostMapping
	public Favori enregistrerFavori(@RequestBody FavoriDtoPost favoriCreationDto) {
		Favori favori = transformerFavori.favoriCreationDtoToFavori();
		return FavoriService.insererEnBase(favori);

	}

	@DeleteMapping
	public void supprimerFavori(@RequestBody Favori favori) {
		// FavoriService.supprimerFavori(Favori);
	}

}
