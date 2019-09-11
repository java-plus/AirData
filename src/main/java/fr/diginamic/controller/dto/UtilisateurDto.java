package fr.diginamic.controller.dto;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Favori;
import fr.diginamic.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private String id;
    private List<Role> role;
    private String identifiant;
    private String email;
    private Integer age;
    List<Favori> listeFavori;
    Commune commune;
    CompteUtilisateur compteUtilisateur;
}
