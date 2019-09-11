package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

	@Query("select u.compteUtilisateur from Utilisateur u where login=u.identifiant")
	CompteUtilisateur findCompteUtilisateurWithIdentifiant(String login);
}
