package fr.diginamic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.diginamic.entites.Commune;

/**
 * Ce repository gère les communes dans la base de donnée
 * 
 * @author Diginamic02
 *
 */
@Repository
public interface CommuneRepository extends JpaRepository<Commune, Integer> {

	/**
	 * Methode Cette methode trouve une Commune en base de donnée en fonction de
	 * son Id
	 * 
	 * @param codeCommune le code de la commune
	 * @return un Optional<Commune>
	 */
	public Optional<Commune> findByCodeCommune(String codeCommune);
}
