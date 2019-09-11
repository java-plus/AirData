/**
 * 
 */
package fr.diginamic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entites.Favori;

/**
 * @author Eloi
 *
 */
public interface FavoriRepository extends JpaRepository<Favori, Integer> {

}
