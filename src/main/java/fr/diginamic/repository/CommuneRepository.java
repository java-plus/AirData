package fr.diginamic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.Commune;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Integer> {

	public Optional<Commune> findByCodeCommune(String codeCommune);

}
