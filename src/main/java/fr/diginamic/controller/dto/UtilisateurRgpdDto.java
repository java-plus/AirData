package fr.diginamic.controller.dto;


import fr.diginamic.entites.Commune;
import fr.diginamic.entites.CompteUtilisateur;
import fr.diginamic.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRgpdDto {


    private String id;
    private List<Role> role;
    private String identifiant;
    private String email;
    private Integer age;
    List<FavoriSansUtilisateurDto> listeFavori;
    Commune commune;
    CompteUtilisateur compteUtilisateur;
}
