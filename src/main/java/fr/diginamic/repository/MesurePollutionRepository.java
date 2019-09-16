
package fr.diginamic.repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.MesurePollution;

/**
 * Ce repository gère les MesurePollution en base de donnée
 * 
 * @author Diginamic02
 *
 */
@Repository
public interface MesurePollutionRepository extends JpaRepository<MesurePollution, String> {

	/**
	 * Cette methode retourne une MesurePollution présente en base de donnée en
	 * fonction de son id
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	public Optional<MesurePollution> findById(String id);

	/**
	 * Cette methode retourne une liste de MesurePollution (une pour le SO2, une
	 * pour le PM2.5, PM10, O3, NO2, CO) pour une commune donnée (via le code
	 * commune) Plus précisemment, la requete JPQL de cette methode prend la
	 * liste des MesurePollution dont la station de Mesure correspond à la
	 * station de mesure d'une commune donnée (via le codeCommune). Par exemple,
	 * si l'utilisateur indique le code commune de Nantes, la requete JPQL
	 * recherche la station de mesure pollution correspondant à Nantes indiquée
	 * en base de donnée (et donc la plus proche) pour chaque polluant puis
	 * cherche les MesurePollution correspondantes qu'a fourni cette station.
	 * 
	 * @param code
	 * @return
	 */
	default List<MesurePollution> obtenirLesMesuresDePollution(String code) {

		List<List<MesurePollution>> listeDeListesMesurePollution = new ArrayList<List<MesurePollution>>();
		List<MesurePollution> listeDeMesurePollution = new ArrayList<MesurePollution>();
		List<MesurePollution> mesurePollutionSO2 = obtenirLaMesureDeSO2(code);
		List<MesurePollution> mesurePollutionPM25 = obtenirLaMesureDePM25(code);
		List<MesurePollution> mesurePollutionPM10 = obtenirLaMesureDePM10(code);
		List<MesurePollution> mesurePollutionO3 = obtenirLaMesureDeO3(code);
		List<MesurePollution> mesurePollutionNO2 = obtenirLaMesureDeNO2(code);
		List<MesurePollution> mesurePollutionCO = obtenirLaMesureDeCO(code);

		listeDeListesMesurePollution.add(mesurePollutionSO2);
		listeDeListesMesurePollution.add(mesurePollutionPM25);
		listeDeListesMesurePollution.add(mesurePollutionPM10);
		listeDeListesMesurePollution.add(mesurePollutionO3);
		listeDeListesMesurePollution.add(mesurePollutionNO2);
		listeDeListesMesurePollution.add(mesurePollutionCO);

		for (List<MesurePollution> listeDeMesurePollutionAtrier : listeDeListesMesurePollution) {
			MesurePollution mesureLaPlusRecente = new MesurePollution();
			ZonedDateTime dateMesureLaPlusRecente = ZonedDateTime.now().minusYears(30);
			for (MesurePollution mesurePollution : listeDeMesurePollutionAtrier) {
				if (mesurePollution.getDate().isAfter(dateMesureLaPlusRecente)) {
					dateMesureLaPlusRecente = mesurePollution.getDate();
					mesureLaPlusRecente = mesurePollution;
				}
			}
			listeDeMesurePollution.add(mesureLaPlusRecente);

		}

		return listeDeMesurePollution;

	}

	/**
	 * cette Methode retourne la liste des mesures de pollution SO2 qu'a fourni
	 * une station relative à une commune
	 * 
	 * @param code
	 * @return
	 */
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureSO2 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'SO2\'")
	List<MesurePollution> obtenirLaMesureDeSO2(@Param("code") String code);

	/**
	 * cette Methode retourne la liste des mesures de pollution PM2.5 qu'a
	 * fourni une station relative à une commune
	 * 
	 * @param code
	 * @return
	 */
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesurePM25 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'PM2.5\'")
	List<MesurePollution> obtenirLaMesureDePM25(@Param("code") String code);

	/**
	 * cette Methode retourne la liste des mesures de pollution PM10 qu'a fourni
	 * une station relative à une commune
	 * 
	 * @param code
	 * @return
	 */
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesurePM10 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'PM10\'")
	List<MesurePollution> obtenirLaMesureDePM10(@Param("code") String code);

	/**
	 * cette Methode retourne la liste des mesures de pollution O3 qu'a fourni
	 * une station relative à une commune
	 * 
	 * @param code
	 * @return
	 */
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureO3 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'O3\'")
	List<MesurePollution> obtenirLaMesureDeO3(@Param("code") String code);

	/**
	 * cette Methode retourne la liste des mesures de pollution NO2 qu'a fourni
	 * une station relative à une commune
	 * 
	 * @param code
	 * @return
	 */
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureNO2 from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'NO2\'")
	List<MesurePollution> obtenirLaMesureDeNO2(@Param("code") String code);

	/**
	 * cette Methode retourne la liste des mesures de pollution CO qu'a fourni
	 * une station relative à une commune
	 * 
	 * @param code
	 * @return
	 */
	@Query("select m from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=:code) and m.typeDeDonnee=\'CO\'")
	List<MesurePollution> obtenirLaMesureDeCO(@Param("code") String code);

	/**
	 * 
	 * Méthode qui retourne une liste de d'Objet contenant les mesures de O3 et
	 * les dates, comprises entre 2 dates pour une commune. la valeur (index
	 * [0]) est un Double et la date (index[1]) est une ZonedDateTime
	 * 
	 * @param codeCommune
	 *            (le code insee (code commune) de la commune)
	 * @param dateDebut
	 *            (ZonedDateTime de début)
	 * @param dateFin
	 *            (ZonedDateTime de fin)
	 * @return
	 */
	@Query("select valeur, date from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureO3 from Commune c where c.codeCommune=?1) and  m.typeDeDonnee=\'O3\' and m.date between?2 and?3")
	Optional<List<Object[]>> obtenirLesO3ParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

	/**
	 * 
	 * Méthode qui retourne une liste de d'Objet contenant les mesures de PM10
	 * et les dates, comprises entre 2 dates pour une commune. la valeur (index
	 * [0]) est un Double et la date (index[1]) est une ZonedDateTime
	 * 
	 * @param codeCommune
	 *            (le code insee (code commune) de la commune)
	 * @param dateDebut
	 *            (ZonedDateTime de début)
	 * @param dateFin
	 *            (ZonedDateTime de fin)
	 * @return
	 */
	@Query("select valeur, date from MesurePollution m where m.stationDeMesure=(select c.stationDeMesurePM10 from Commune c where c.codeCommune=?1) and m.typeDeDonnee=\'PM10\' and m.date between?2 and?3")
	Optional<List<Object[]>> obtenirLesPM10ParPeriode(String codeCommune, ZonedDateTime dateDebut,
			ZonedDateTime dateFin);

	/**
	 * 
	 * Méthode qui retourne une liste de d'Objet contenant les mesures de PM25
	 * et les dates, comprises entre 2 dates pour une commune. la valeur (index
	 * [0]) est un Double et la date (index[1]) est une ZonedDateTime
	 * 
	 * @param codeCommune
	 *            (le code insee (code commune) de la commune)
	 * @param dateDebut
	 *            (ZonedDateTime de début)
	 * @param dateFin
	 *            (ZonedDateTime de fin)
	 * @return
	 */
	@Query("select valeur, date from MesurePollution m where m.stationDeMesure=(select c.stationDeMesurePM25 from Commune c where c.codeCommune=?1)and m.typeDeDonnee=\'PM2.5\'and m.date between?2 and?3")
	Optional<List<Object[]>> obtenirLesPM25ParPeriode(String codeCommune, ZonedDateTime dateDebut,
			ZonedDateTime dateFin);

	/**
	 * 
	 * Méthode qui retourne une liste de d'Objet contenant les mesures de NO2 et
	 * les dates, comprises entre 2 dates pour une commune. la valeur (index
	 * [0]) est un Double et la date (index[1]) est une ZonedDateTime
	 * 
	 * @param codeCommune
	 *            (le code insee (code commune) de la commune)
	 * @param dateDebut
	 *            (ZonedDateTime de début)
	 * @param dateFin
	 *            (ZonedDateTime de fin)
	 * @return
	 */
	@Query("select valeur, date from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureNO2 from Commune c where c.codeCommune=?1)and m.typeDeDonnee=\'NO2\'and m.date between?2 and?3")
	Optional<List<Object[]>> obtenirLesNO2ParPeriode(String codeCommune, ZonedDateTime dateDebut,
			ZonedDateTime dateFin);

	/**
	 * 
	 * Méthode qui retourne une liste de d'Objet contenant les mesures de S02 et
	 * les dates, comprises entre 2 dates pour une commune. la valeur (index
	 * [0]) est un Double et la date (index[1]) est une ZonedDateTime
	 * 
	 * @param codeCommune
	 *            (le code insee (code commune) de la commune)
	 * @param dateDebut
	 *            (ZonedDateTime de début)
	 * @param dateFin
	 *            (ZonedDateTime de fin)
	 * @return
	 */
	@Query("select valeur, date from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureSO2 from Commune c where c.codeCommune=?1)and m.typeDeDonnee=\'SO2\'and m.date between?2 and?3")
	Optional<List<Object[]>> obtenirLesS02ParPeriode(String codeCommune, ZonedDateTime dateDebut,
			ZonedDateTime dateFin);

	/**
	 * 
	 * Méthode qui retourne une liste de d'Objet contenant les mesures de CO et
	 * les dates, comprises entre 2 dates pour une commune. la valeur (index
	 * [0]) est un Double et la date (index[1]) est une ZonedDateTime
	 * 
	 * @param codeCommune
	 *            (le code insee (code commune) de la commune)
	 * @param dateDebut
	 *            (ZonedDateTime de début)
	 * @param dateFin
	 *            (ZonedDateTime de fin)
	 * @return
	 */
	@Query("select valeur, date from MesurePollution m where m.stationDeMesure=(select c.stationDeMesureCO from Commune c where c.codeCommune=?1)and m.typeDeDonnee=\'CO\'and m.date between?2 and?3")
	Optional<List<Object[]>> obtenirLesCOParPeriode(String codeCommune, ZonedDateTime dateDebut, ZonedDateTime dateFin);

}
