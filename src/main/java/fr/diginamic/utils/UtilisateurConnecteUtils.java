package fr.diginamic.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.diginamic.controller.dto.UtilisateurConnecteService;
import fr.diginamic.repository.UtilisateurRepository;

/**
 * Cette classe permet d'avoir des information sur l'utilisateur connecté
 * 
 * @author Diginamic02
 *
 */
@Component
public class UtilisateurConnecteUtils {

	private UtilisateurRepository utilisateurRepository;

	public UtilisateurConnecteUtils(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	/**
	 * Cette methode permet de récupérer l'identifiant de l'utilisateur connecté
	 * 
	 * @return
	 */
	public static String recupererIdentifiant() {
		return ((UtilisateurConnecteService) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
	}

	/**
	 * Cette methode permet de récupérer le code commune de la commune de
	 * l'utilisateur connecté (la commune renseignée lors de son inscription ou
	 * dans son compte, pas sa géolocalisation).
	 * 
	 * @return
	 */
	public static String recupererCodeCommune() {
		return ((UtilisateurConnecteService) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getCodeCommune();
	}

}
