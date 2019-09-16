package fr.diginamic.controller.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * représente l'utilisateur présent dans le context de l'application. Pour être
 * dans le context, cet utilisateur doit être connecté.
 * 
 * @author Diginamic02
 *
 */
@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurConnecteService {

	/**
	 * username : String représente le username (ou identifiant ou login) de
	 * l'utilisateur
	 */
	private String username;
	/**
	 * codeCommune : String représente le code commune de l'utilisateur présent
	 * en BDD
	 */
	private String codeCommune;

}
