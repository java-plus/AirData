package fr.diginamic.repository;

import fr.diginamic.entites.CompteUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * gère l’acces à la base de données pour le compte utilisateur
 */
public interface CompteUtilisateurRepository extends JpaRepository<CompteUtilisateur,Integer> {
}
