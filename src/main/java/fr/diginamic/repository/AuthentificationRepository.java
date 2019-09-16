package fr.diginamic.repository;

import fr.diginamic.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * gere l’acces à la base de données pour la connexion de l’utilisateur
 */
@Repository
public interface AuthentificationRepository extends JpaRepository<Utilisateur,String> {

    /**
     * recupere un utilisateur grace à son identifiant
     * @param identifiant identifiant de l’utilisateur
     * @return un Optional<Utilisateur>
     */
    Optional<Utilisateur> findByIdentifiant(String identifiant);
}
