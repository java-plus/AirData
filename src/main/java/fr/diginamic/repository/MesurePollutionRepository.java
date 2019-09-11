package fr.diginamic.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.MesurePollution;

@Repository
public interface MesurePollutionRepository extends JpaRepository<MesurePollution, Integer> {

	default List<MesurePollution> obtenirLesMesuresDePollution(String code) {

		List<MesurePollution> listeDeMesurePollution = new ArrayList<MesurePollution>();
		List<MesurePollution> mesurePollutionSO2 = obtenirLaMesureDeSO2(code);
		listeDeMesurePollution.add(mesurePollutionSO2.get(0));
		return listeDeMesurePollution;

	}

	// private Boolean mesureSO2 = false;
	// private Boolean mesurePM25 = false;
	// private Boolean mesurePM10 = false;
	// private Boolean mesureO3 = false;
	// private Boolean mesureNO2 = false;
	// private Boolean mesureCO = false;
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureSO2 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'SO2\'")
	List<MesurePollution> obtenirLaMesureDeSO2(@Param("code") String code);

}
