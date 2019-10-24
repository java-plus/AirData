
package fr.diginamic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.controller.dto.AnalyseMesureDtoPost;
import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.entites.MesurePollution;
import fr.diginamic.service.AnalyseMesureService;
import fr.diginamic.service.MesureMeteoService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.utils.UtilisateurConnecteUtils;

/**
 * Cette classe gère les appels API suivants: "/mesures/pollution" permet
 * d'obtenir les mesures de pollution concernant la commune enregistrée dans le
 * compte de l'utilisateur. "/mesures/pollution?codeCommune=44108" permet
 * d'obtenir les mesures de pollution concernant la commune représentée par le
 * codeCommune renseigné (ici le code commune est 44108). "/mesures/meteo"
 * permet d'obtenir les mesures de meteo concernant la commune enregistrée dans
 * le compte de l'utilisateur. "/mesures/meteo?codeCommune=44108" permet
 * d'obtenir les mesures de meteo concernant la commune représentée par le
 * codeCommune renseigné (ici le code commune est 44108).
 * 
 * @author Diginamic02
 *
 */
@RestController
@RequestMapping("/mesures")
public class MesureController {

	/**
	 * Un service de mesure de pollution
	 */
	@Autowired
	private MesurePollutionService mesurePollutionService;

	/**
	 * Un service d’analyse de mesure
	 */
	@Autowired
	private AnalyseMesureService analyseMesureService;

	@Autowired
	private MesureMeteoService mesureMeteoService;

	/**
	 * Cette methode permet d’obtenir l’historique d’un indicateur
	 * 
	 * @param analyseMesureDtoPost
	 *            objet contenant les parametres de la recherche
	 * @return AnalyseMesureDto
	 */
	@PostMapping
	public AnalyseMesureDto recupererHistoriqueIndicateur(@RequestBody AnalyseMesureDtoPost analyseMesureDtoPost) {

		return analyseMesureService.recupererHistoriqueIndicateur(analyseMesureDtoPost);
	}

	/**
	 * Cette methode gère l'appel de l'url "/mesures/pollution" permet d'obtenir
	 * les mesures de pollution concernant la commune enregistrée dans le compte
	 * de l'utilisateur.
	 * 
	 * @return Une liste de mesure de pollution
	 */
	@GetMapping("/pollution")
	public List<MesurePollution> obtenirLesMesuresPollution() {

		String codeCommune = UtilisateurConnecteUtils.recupererCodeCommune();

		return mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);

	}

	/**
	 * Cette methode gère l'appel de l'url "/mesures/meteo?codeCommune=44108" et
	 * permet d'obtenir les mesures de meteo concernant la commune représentée
	 * par le codeCommune renseigné (ici le code commune est 44108).
	 * 
	 * @param codeCommune
	 * @return une liste de mesure de pollution
	 */
	@GetMapping(path = "/pollution", params = { "codeCommune" })
	public List<MesurePollution> obtenirLesMesuresPollution(@RequestParam String codeCommune) {

		return mesurePollutionService.obtenirLesMesuresDePollution(codeCommune);

	}

	/**
	 * Cette methode gère l'appel de l'url "/mesures/meteo" permet d'obtenir les
	 * mesures de meteo concernant la commune enregistrée dans le compte de
	 * l'utilisateur.
	 * 
	 * @return une mesure meteo
	 */
	@GetMapping("/meteo")
	public MesureMeteo obtenirLesMesuresDeMeteo() {

		String codeCommune = UtilisateurConnecteUtils.recupererCodeCommune();

		return mesureMeteoService.obtenirLesMesuresDeMeteo(codeCommune);

	}

	/**
	 * Cette methode gère l'appel de l'url "/mesures/meteo?codeCommune=44108"
	 * permet d'obtenir les mesures de meteo concernant la commune représentée
	 * par le codeCommune renseigné (ici le code commune est 44108).
	 * 
	 * @param codeCommune
	 * @return une mesure meteo
	 */
	@GetMapping(path = "/meteo", params = { "codeCommune" })
	public MesureMeteo obtenirLesMesuresMeteo(@RequestParam String codeCommune) {

		return mesureMeteoService.obtenirLesMesuresDeMeteo(codeCommune);

	}

}
