package fr.diginamic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.MesurePollution;

@Repository
public interface MesurePollutionRepository extends JpaRepository<MesurePollution, String> {

	public Optional<MesurePollution> findById(String id);

	default List<MesurePollution> obtenirLesMesuresDePollution(String code) {

		List<MesurePollution> listeDeMesurePollution = new ArrayList<MesurePollution>();
		List<MesurePollution> mesurePollutionSO2 = obtenirLaMesureDeSO2(code);
		List<MesurePollution> mesurePollutionPM25 = obtenirLaMesureDePM25(code);
		List<MesurePollution> mesurePollutionPM10 = obtenirLaMesureDePM10(code);
		List<MesurePollution> mesurePollutionO3 = obtenirLaMesureDeO3(code);
		List<MesurePollution> mesurePollutionNO2 = obtenirLaMesureDeNO2(code);
		List<MesurePollution> mesurePollutionCO = obtenirLaMesureDeCO(code);

		listeDeMesurePollution.add(mesurePollutionSO2.get(0));
		listeDeMesurePollution.add(mesurePollutionPM25.get(0));
		listeDeMesurePollution.add(mesurePollutionPM10.get(0));
		listeDeMesurePollution.add(mesurePollutionO3.get(0));
		listeDeMesurePollution.add(mesurePollutionNO2.get(0));
		listeDeMesurePollution.add(mesurePollutionCO.get(0));

		return listeDeMesurePollution;

	}

	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureSO2 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'SO2\'")
	List<MesurePollution> obtenirLaMesureDeSO2(@Param("code") String code);

	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesurePM25 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'PM2.5\'")
	List<MesurePollution> obtenirLaMesureDePM25(@Param("code") String code);

	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesurePM10 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'PM10\'")
	List<MesurePollution> obtenirLaMesureDePM10(@Param("code") String code);

	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureO3 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'O3\'")
	List<MesurePollution> obtenirLaMesureDeO3(@Param("code") String code);

	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureNO2 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'NO2\'")
	List<MesurePollution> obtenirLaMesureDeNO2(@Param("code") String code);

	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'CO\'")
	List<MesurePollution> obtenirLaMesureDeCO(@Param("code") String code);
	//
	// @Query("select valeur from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=?1) and
	// m.typeDeDonnee=\'O3\' and m.date between ?2 and ?3")
	// Double obtenirLesO3ParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);
	//
	// @Query("select valeur from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=?1) and
	// m.typeDeDonnee=\'PM10\' and m.date between ?2 and ?3")
	// Double obtenirLesPM10ParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);
	//
	// @Query("select valeur from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=?1) and
	// m.typeDeDonnee=\'PM25\' and m.date between ?2 and ?3")
	// Double obtenirLesPM25ParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);
	//
	// @Query("select valeur from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=?1) and
	// m.typeDeDonnee=\'NO2\' and m.date between ?2 and ?3")
	// Double obtenirLesNO2ParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);
	//
	// @Query("select valeur from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=?1) and
	// m.typeDeDonnee=\'CO\' and m.date between ?2 and ?3")
	// Double obtenirLesS02ParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

}
