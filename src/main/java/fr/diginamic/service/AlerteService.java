package fr.diginamic.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.Alerte;
import fr.diginamic.exception.AlerteInvalideException;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.AlerteRepository;
import fr.diginamic.repository.CommuneRepository;

/**
 * Cette classe gère l'insertion en BDD des alertes et l'obtention des alertes
 * présentes en BDD
 * 
 * @author Diginamic02
 *
 */
@Service
public class AlerteService {

	private AlerteRepository alerteRepository;
	private CommuneRepository communeRepository;

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	public AlerteService(AlerteRepository alerteRepository, CommuneRepository communeRepository) {
		this.alerteRepository = alerteRepository;
		this.communeRepository = communeRepository;
	}

	/**
	 * Cette methode permet de 'enregistrer en BDD un nouvel objet Alerte. La
	 * methode retrouve le code commune de la commune renseignée s'il n'est pas
	 * encore présent puis vérifie si les attributs de l'objet sont corrects
	 * puis l'insère en base de donnée. Si le code commune n'est pas trouvé en
	 * BDD, alors une exception CommuneNonTrouveeException est renvoyée Si les
	 * attributs de l'objet Alerte sont incorrects, alors une exception
	 * AlerteInvalideException est renvoyée
	 * 
	 * @param alerte
	 * @return
	 */
	public Alerte creerAlerte(Alerte alerte) {
		if (alerte.getCodeCommune() != null) {
			communeRepository.findByCodeCommune(alerte.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);
		}
		if (validator.validate(alerte).isEmpty()) {
			alerteRepository.save(alerte);
		} else {
			throw new AlerteInvalideException();
		}
		return alerte;
	}

	/**
	 * Cette methode permet d'obtenir la liste des Alertes enregistrées en BDD
	 * selon plusieurs critères de recherche: la région, le département, la
	 * commune (via le code commune)
	 * 
	 * @param alerte
	 * @return
	 */
	public List<Alerte> recupererAlerte(Alerte alerte) {
		List<Alerte> listeAlertes = new ArrayList<>();
		if (alerte.getRegion() != null) {
			listeAlertes = alerteRepository.findAlerteWithRegion(alerte.getType(), alerte.getRegion(),
					ZonedDateTime.now());
		} else if (alerte.getDepartement() != null) {
			listeAlertes = alerteRepository.findAlerteWithDepartement(alerte.getType(), alerte.getDepartement(),
					ZonedDateTime.now());
		} else if (alerte.getCodeCommune() != null) {
			listeAlertes = alerteRepository.findAlerteWithCodeCommune(alerte.getType(), alerte.getCodeCommune(),
					ZonedDateTime.now());
		}
		return listeAlertes;
	}
}
