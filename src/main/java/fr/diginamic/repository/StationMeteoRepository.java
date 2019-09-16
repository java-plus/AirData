package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.StationDeMesureMeteo;

@Repository
public interface StationMeteoRepository extends JpaRepository<StationDeMesureMeteo, Integer> {

	StationDeMesureMeteo findByLatitudeAndLongitude(Double latitude, Double longitude);

}
