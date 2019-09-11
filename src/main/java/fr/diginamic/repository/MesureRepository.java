package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entites.MesurePollution;

public interface MesureRepository extends JpaRepository<MesurePollution, String> {

}
