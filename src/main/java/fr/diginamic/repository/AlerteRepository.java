package fr.diginamic.repository;

import fr.diginamic.entites.Alerte;
import fr.diginamic.entites.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte,Integer> {

    @Query("select a from Alerte a where a.type =?1 and a.region=?2 and a.dateFin>?3")
    List<Alerte> findAlerteWithRegion(Type type, String region, ZonedDateTime dateActuelle);

    @Query("select a from Alerte a where a.type =?1 and a.departement=?2 and a.dateFin>?3")
    List<Alerte> findAlerteWithDepartement(Type type, String departement, ZonedDateTime dateActuelle);

    @Query("select a from Alerte a where a.type =?1 and a.codeCommune=?2 and a.dateFin>?3")
    List<Alerte> findAlerteWithCodeCommune(Type type, String codeCommune, ZonedDateTime dateActuelle);

}
