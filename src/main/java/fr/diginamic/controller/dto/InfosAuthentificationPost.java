package fr.diginamic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfosAuthentificationPost {

    private String identifiant;
    private String mdp;
}
