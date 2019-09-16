package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * represente le role d’un utilisateur
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * id du role
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * nom du role
     */
    private String libelle;

    /**
     * constructeur
     * @param libelle nom du role
     */
    public Role(String libelle){
        this.libelle = libelle;

    }
}
