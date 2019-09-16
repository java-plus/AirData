package fr.diginamic.repository;

import fr.diginamic.entites.Alerte;
import fr.diginamic.entites.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * gere les acces en base de données pour les alertes
 */
@Repository
public interface AlerteRepository extends JpaRepository<Alerte,Integer> {

    /**
     * recupere les alertes pour la region
     * @param type type de l’alerte
     * @param region la region de l’alerte
     * @param dateActuelle la date actuelle
     * @return une liste d’alerte
     */
    @Query("select a from Alerte a where a.type =?1 and a.region=?2 and a.dateFin>?3")
    List<Alerte> findAlerteWithRegion(Type type, String region, ZonedDateTime dateActuelle);

    /**
     * recupere les alertes pour le departement
     * @param type type de l’alerte
     * @param departement le departement de l’alerte
     * @param dateActuelle la date actuelle
     * @return une liste d’alerte
     */
    @Query("select a from Alerte a where a.type =?1 and a.departement=?2 and a.dateFin>?3")
    List<Alerte> findAlerteWithDepartement(Type type, String departement, ZonedDateTime dateActuelle);

    /**
     * recupere les alertes pour la commune
     * @param type type de l’alerte
     * @param codeCommune la commune de l’alerte
     * @param dateActuelle la date actuelle
     * @return une liste d’alerte
     */
    @Query("select a from Alerte a where a.type =?1 and a.codeCommune=?2 and a.dateFin>?3")
    List<Alerte> findAlerteWithCodeCommune(Type type, String codeCommune, ZonedDateTime dateActuelle);

}
