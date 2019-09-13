package fr.diginamic.controller.dto;

import fr.diginamic.entites.Commune;
import fr.diginamic.entites.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriSansUtilisateurDto {


    private Commune commune;

    private Boolean weatherDescription =false;
    private Boolean weatherIcon=false;
    private Boolean temperature=false;
    private Boolean pressure=false;
    private Boolean humidity=false;
    private Boolean tempMin=false;
    private Boolean tempMax=false;
    private Boolean windSpeed=false;
    private Boolean windDegrees=false;

    private Boolean mesureSO2=false;
    private Boolean mesurePM25=false;
    private Boolean mesurePM10=false;
    private Boolean mesureO3=false;
    private Boolean mesureNO2=false;
    private Boolean mesureCO=false;

}
