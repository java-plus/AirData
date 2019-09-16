package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.StationDeMesureMeteo;

/**
 * gere l’acces à la base pour les stations meteo
 */
@Repository
public interface StationMeteoRepository extends JpaRepository<StationDeMesureMeteo, Integer> {

	/**
	 * recupere une station de mesure meteo grace à sa latitude et longitude
	 * @param latitude latitude de la station
	 * @param longitude longitude de la station
	 * @return une station meteo
	 */
	StationDeMesureMeteo findByLatitudeAndLongitude(Double latitude, Double longitude);

}
