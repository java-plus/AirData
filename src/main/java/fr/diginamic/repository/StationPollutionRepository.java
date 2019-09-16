package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.StationDeMesurePollution;

@Repository
public interface StationPollutionRepository extends JpaRepository<StationDeMesurePollution, Integer> {

	StationDeMesurePollution findByLatitudeAndLongitude(Double latitude, Double longitude);

}
