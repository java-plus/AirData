/**
 * 
 */
package fr.diginamic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.diginamic.entites.Favori;
import fr.diginamic.entites.Utilisateur;

public interface FavoriRepository extends JpaRepository<Favori, Integer> {

	/**
	 * 
	 * Requete qui récupère une liste de tous les favori d'un utilisateur
	 * 
	 * @param utilisateur
	 * 
	 * @return un Optional<List<Favori>>
	 */
	@Query("select f from Favori f where f.utilisateur=?1")
	Optional<List<Favori>> findByUtilisateurId(Utilisateur utilisateur);

}
