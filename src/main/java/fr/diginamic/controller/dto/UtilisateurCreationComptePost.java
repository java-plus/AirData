package fr.diginamic.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * représente le corps de la requete http en POST des urls /compte et
 * /compte_admin.
 * 
 * @author Diginamic02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurCreationComptePost {

	/** identifiant : String représente l'identifiant (ou login) renseigné */
	@NotBlank
	private String identifiant;
	/**
	 * motDePasse : String représente le mot de passe renseigné, celui-ci doit
	 * contenir 8 caractères minimum, 1 majuscule, 1 minuscule, 1 chiffre et un
	 * caractère spécial
	 */
	@NotBlank
	@Size(min = 8)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=\\S+$).{8,}$")
	private String motDePasse;
	/** email : String String représente l'email renseigné */
	@Email
	private String email;
	/** age : Integer Représente l'age renseigné */
	@Positive
	@NotNull
	private Integer age;
	/**
	 * codeCommune : String représente le code Commune de la commune renseignée
	 */
	@NotBlank
	@Pattern(regexp = "^[0-9]*$")
	private String codeCommune;

}
