package fr.diginamic.repository;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {

	@Query("select u.compteUtilisateur from Utilisateur u where u.identifiant=?1")
	CompteUtilisateur findCompteUtilisateurWithIdentifiant(String identifiant);

	@Query("select u.id from Utilisateur u where u.identifiant=?1")
	String findIdWithIdentifiant(String identifiant);

	Optional<Utilisateur> findByIdentifiant(String identifiant);
}
