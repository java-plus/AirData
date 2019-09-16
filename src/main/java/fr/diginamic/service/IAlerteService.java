package fr.diginamic.service;

import fr.diginamic.entites.Alerte;

import java.util.List;

public interface IAlerteService {
    Alerte creerAlerte(Alerte alerte);

    List<Alerte> recupererAlerte(Alerte alerte);
}
