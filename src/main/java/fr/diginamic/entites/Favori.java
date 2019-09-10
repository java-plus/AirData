package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    Commune commune;

    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID")
    private Utilisateur utilisateur;

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
