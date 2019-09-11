package fr.diginamic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfosAuthentificationPost {

    private String login;
    private String mdp;
}
