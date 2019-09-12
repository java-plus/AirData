package fr.diginamic.repository;

import fr.diginamic.entites.CompteUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteUtilisateurRepository extends JpaRepository<CompteUtilisateur,Integer> {
}
