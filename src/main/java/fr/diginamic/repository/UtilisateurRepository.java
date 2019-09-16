package fr.diginamic.repository;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;

import java.util.Optional;

/**
 * gere l’acces à la base de données pour les utilisateurs
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {

	/**
	 * récupere un compte utilisateur grace à l’identifiant de l’utilisateur
	 * @param identifiant identifiant de l’utilisateur
	 * @return un compte utilisateur
	 */
	@Query("select u.compteUtilisateur from Utilisateur u where u.identifiant=?1")
	CompteUtilisateur findCompteUtilisateurWithIdentifiant(String identifiant);

	/**
	 * recupere l’identifiant de l’utilisateur grace à l’identifiant
	 * @param identifiant identifiant de l’utilisateur
	 * @return l’id de l’utilisateur
	 */
	@Query("select u.id from Utilisateur u where u.identifiant=?1")
	String findIdWithIdentifiant(String identifiant);

	/**
	 * recupere un utilisateur grace à son identifiant
	 * @param identifiant identifiant de l’utilisateur
	 * @return un Optional<Utilisateur>
	 */
	Optional<Utilisateur> findByIdentifiant(String identifiant);
}
