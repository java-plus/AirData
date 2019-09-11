package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entites.Commune;

public interface CommuneRepository extends JpaRepository<Commune, String> {

}
