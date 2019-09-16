package fr.diginamic.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.MesureMeteo;

@Repository
public interface MesureMeteoRepository extends JpaRepository<MesureMeteo, Long> {

	public Optional<MesureMeteo> findById(Long id);

	@Query("select m from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=:code)")
	List<MesureMeteo> obtenirLesMesuresDeMeteo(@Param("code") String codeCommune);

	@Query("select m, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirTousLesIndicateursParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select temperature, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesTemperatureParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select pressure, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesPressureParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select humidity, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesHumidityParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select tempMin, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesTempsMinParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select tempMax, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesTempsMaxParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select windSpeed, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesWindSpeedParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	@Query("select windDegrees, date from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=?1) and m.date between ?2 and ?3")
	Optional<List<MesureMeteo>> obtenirLesWindDegreesParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

}
