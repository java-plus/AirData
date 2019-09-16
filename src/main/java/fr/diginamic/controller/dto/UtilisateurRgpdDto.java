package fr.diginamic.controller.dto;

import java.util.List;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un DTO (ou VUE) RGPD de l'utilisateur. Cette classe est appellée
 * quand l'utilisateur demande a avoir accès aux informations le concernant
 * présentes en BDD (url: /utilisateur_rgpd)
 * 
 * @author Diginamic02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRgpdDto {

	/** id : String représente l'id de l'utilisateur */
	private String id;
	/**
	 * role : List<Role> représente les differents rôles attribués à
	 * l'utilisateur ROLE_ADMIN ou ROLE_USER
	 */
	private List<Role> role;
	/**
	 * identifiant : String représente l'identifiant (ou login ou pseudo) de
	 * l'utilisateur
	 */
	private String identifiant;
	/** email : String rerpésente l'email de l'utilisateur */
	private String email;
	/** age : Integer représente l'age de l'utilisateur */
	private Integer age;
	/**
	 * listeFavori : List<FavoriSansUtilisateurDto> représente la liste des
	 * favoris créés par l'utilisateur
	 */
	List<FavoriSansUtilisateurDto> listeFavori;
	/** commune : Commune représente la commune de l'utilisateur */
	Commune commune;
	/**
	 * compteUtilisateur : CompteUtilisateur représente le compte utilisateur de
	 * l'utilisateur, celui-ci contient lui même diverses informations
	 */
	CompteUtilisateur compteUtilisateur;
}
