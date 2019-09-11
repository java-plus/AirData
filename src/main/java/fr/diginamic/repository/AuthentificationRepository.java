package fr.diginamic.repository;

import fr.diginamic.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthentificationRepository extends JpaRepository<Utilisateur,String> {

    Optional<Utilisateur> findByIdentifiant(String identifiant);
}
