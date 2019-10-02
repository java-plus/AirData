package fr.diginamic.controller.dto;

import java.util.List;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Favori;
import fr.diginamic.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente une vue (ou DTO) d'un utilisateur.
 * 
 * @author Diginamic02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

	private String id;
	/**
	 * role : List<Role> représente la liste des rôle caractérisant
	 * l'utilisateur, ROLE_ADMIN ou ROLE_USER. Le ROLE_USER n'a pas accès à
	 * certaines pages de l'application
	 */
	private List<Role> role;
	/**
	 * identifiant : String représente l'identifiant (ou login ou username) de
	 * l'utilisateur
	 */
	private String identifiant;
	/** email : String représente l'email de l'utilisateur */
	private String email;
	/** age : Integer représente l'age de l'utilisateur */
	private Integer age;
	/**
	 * listeFavori : List<Favori> représente la liste des favoris de
	 * l'utilisateur
	 */
	List<FavoriSansUtilisateurDto> listeFavori;
	/** commune : Commune représente la commune de l'utilisateur */
	Commune commune;
	/**
	 * compteUtilisateur : CompteUtilisateur représente le CompteUtilisateur de
	 * l'utilisateur
	 */
	CompteUtilisateur compteUtilisateur;
}
