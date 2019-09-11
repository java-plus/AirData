package fr.diginamic.transformer;

import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.entites.*;
import fr.diginamic.service.CommuneService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransformerUtilisateur {
    //TODO dto utilisateur supprimer constructeur

    private PasswordEncoder passwordEncoder;

    private CommuneService communeService;

    public TransformerUtilisateur(PasswordEncoder passwordEncoder, CommuneService communeService) {
        this.passwordEncoder = passwordEncoder;
        this.communeService = communeService;
    }

    public Utilisateur UtilisateurCreationComptePostToUtilisateur(UtilisateurCreationComptePost utilisateurCreationComptePost) {
        Utilisateur utilisateur = new Utilisateur();
        List<Role> r = new ArrayList<>();
        r.add(new Role("ROLE_USER"));
        utilisateur.setRole(r);
        utilisateur.setIdentifiant(utilisateurCreationComptePost.getIdentifiant());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurCreationComptePost.getMotDePasse()));
        utilisateur.setEmail(utilisateurCreationComptePost.getEmail());
        utilisateur.setAge(utilisateurCreationComptePost.getAge());
        utilisateur.setListeFavori(new ArrayList<>());
        utilisateur.setCommune(communeService.trouverCommuneParCode(utilisateurCreationComptePost.getCodeCommune()));
        utilisateur.setCompteUtilisateur(new CompteUtilisateur());
        return utilisateur;

    }

    public UtilisateurDto UtilisateurToUtilisateurDto(Utilisateur utilisateur){
        /*private String id;
        private String role;
        private String identifiant;
        private String email;
        private Integer age;
        List<Favori> listeFavori;
        Commune commune;
        CompteUtilisateur compteUtilisateur;*/

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(utilisateur.getId());
        utilisateurDto.setRole(utilisateur.getRole());
        utilisateurDto.setIdentifiant(utilisateur.getIdentifiant());
        utilisateurDto.setEmail(utilisateur.getEmail());
        utilisateurDto.setAge(utilisateur.getAge());
        utilisateurDto.setListeFavori(utilisateur.getListeFavori());
        utilisateurDto.setCommune(utilisateur.getCommune());
        utilisateurDto.setCompteUtilisateur(utilisateur.getCompteUtilisateur());
        return utilisateurDto;
    }
}
