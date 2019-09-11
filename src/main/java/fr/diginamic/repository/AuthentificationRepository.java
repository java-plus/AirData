package fr.diginamic.repository;

import fr.diginamic.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthentificationRepository extends JpaRepository<Utilisateur,String> {

    Optional<Utilisateur> findByIdentifiant(String identifiant);
}
