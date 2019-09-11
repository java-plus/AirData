package fr.diginamic.repository;

import fr.diginamic.entites.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommuneRepository extends JpaRepository<Commune,Integer> {

    public Optional<Commune> findByCodeCommune(String codeCommune);
}
