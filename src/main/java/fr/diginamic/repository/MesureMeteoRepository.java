package fr.diginamic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.MesureMeteo;

/**
 * Ce Repository gère les MesureMeteo en base de donnée
 * 
 * @author Diginamic02
 *
 */
@Repository
public interface MesureMeteoRepository extends JpaRepository<MesureMeteo, Long> {

	/**
	 * Cette methode retrouve en base de donnée une MesureMeteo en fonction de
	 * son id
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	public Optional<MesureMeteo> findById(Long id);

	/**
	 * Methode Cette methode retourne une liste de MesureMeteo concernant une
	 * commune donnée Plus précisemment, la requete JPQL de cette methode prend
	 * la liste des MesureMeteo dont la station de Mesure correspond à la
	 * station de mesure d'une commune donnée (via le codeCommune). Par exemple,
	 * si l'utilisateur indique le code commune de Nantes, la requete JPQL
	 * recherche la station météo correspondant à Nantes indiquée en base de
	 * donnée (et donc la plus proche) puis cherche la liste des MesureMeteo
	 * qu'a fourni cette stationMeteo.
	 * 
	 * @param codeCommune
	 * @return
	 */
	@Query("select m from MesureMeteo m where m.stationDeMesure=(select c.stationDeMesureMeteo from Commune c where c.codeCommune=:code)")
	List<MesureMeteo> obtenirLesMesuresDeMeteo(@Param("code") String codeCommune);

}
