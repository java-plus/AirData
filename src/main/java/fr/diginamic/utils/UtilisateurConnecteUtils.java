package fr.diginamic.utils;


import fr.diginamic.controller.dto.UtilisateurConnecteService;
import fr.diginamic.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurConnecteUtils {

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurConnecteUtils(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public static String recupererIdentifiant(){
        return ((UtilisateurConnecteService) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static String recupererCodeCommune(){
        return ((UtilisateurConnecteService) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getCodeCommune();
    }



}
