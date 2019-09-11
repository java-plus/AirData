package fr.diginamic.repository;

import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,String> {


}
