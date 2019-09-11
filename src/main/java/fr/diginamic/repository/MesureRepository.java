package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entites.MesurePollution;
import org.springframework.stereotype.Repository;

@Repository
public interface MesureRepository extends JpaRepository<MesurePollution, String> {

}
