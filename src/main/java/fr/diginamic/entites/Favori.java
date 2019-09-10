package fr.diginamic.entites;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class Favori {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany
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
