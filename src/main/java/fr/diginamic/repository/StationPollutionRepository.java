package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.StationDeMesurePollution;

/**
 * gere l’acces à la base de données pour les stations de pollution
 */
@Repository
public interface StationPollutionRepository extends JpaRepository<StationDeMesurePollution, Integer> {

	/**
	 * récupere une station de pollution grace à sa latitude et longitude
	 * @param latitude latitude de la station
	 * @param longitude longitude de la station
	 * @return une station de mesure de pollution
	 */
	StationDeMesurePollution findByLatitudeAndLongitude(Double latitude, Double longitude);

}
