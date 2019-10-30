package fr.diginamic.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.Commune;
import fr.diginamic.service.CommuneService;
import fr.diginamic.service.MesurePollutionService;
import fr.diginamic.utils.JsonManipulation;

/**
 * Cette classe gère la récupération du geoJson en BDD
 *
 * @author Diginamic02
 *
 */
@Service
public class GeojsonManager {

	/**
	 * Un service de commune
	 */
	@Autowired
	CommuneService communeService;

	/**
	 * Un service de mesure de pollution
	 */
	@Autowired
	MesurePollutionService mesurePollutionService;

	/**
	 * Cette methode permet d'obtenir la liste complète des communes présentes
	 * en BDD complétées par ses coordonnées polygonales pour geojson et des
	 * données de pollutions
	 * 
	 * @return
	 */
	public String obtenirLaListeDesCommunesAvecPollution() throws Exception {
		List<Commune> listeDesCommunes = communeService.obtenirLaListeDesCommunes();

		return JsonManipulation.obtenirGeoJson2(listeDesCommunes).toString();

	}
}
