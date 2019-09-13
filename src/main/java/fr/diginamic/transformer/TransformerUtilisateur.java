package fr.diginamic.transformer;

import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import fr.diginamic.controller.dto.UtilisateurDto;
import fr.diginamic.controller.dto.UtilisateurRgpdDto;
import fr.diginamic.entites.*;
import fr.diginamic.exception.CreationUtilisateurRgpdDtoImpossibleException;
import fr.diginamic.service.CommuneService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransformerUtilisateur {


    private PasswordEncoder passwordEncoder;

    private CommuneService communeService;

    private TransformerFavori transformerFavori;


    public TransformerUtilisateur(PasswordEncoder passwordEncoder, CommuneService communeService, TransformerFavori transformerFavori) {
        this.passwordEncoder = passwordEncoder;
        this.communeService = communeService;
        this.transformerFavori = transformerFavori;
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

    public UtilisateurDto UtilisateurToUtilisateurDto(Utilisateur utilisateur) {
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

    public UtilisateurRgpdDto utilisateurToUtilisateurRgpdDto(Utilisateur utilisateur) {
        if (utilisateur != null) {
            UtilisateurRgpdDto utilisateurRgpdDto = new UtilisateurRgpdDto();
            utilisateurRgpdDto.setId(utilisateur.getId());
            utilisateurRgpdDto.setRole(utilisateur.getRole());
            utilisateurRgpdDto.setIdentifiant(utilisateur.getIdentifiant());
            utilisateurRgpdDto.setEmail(utilisateur.getEmail());
            utilisateurRgpdDto.setAge(utilisateur.getAge());
            utilisateurRgpdDto.setListeFavori(utilisateur.getListeFavori().stream().map(f -> transformerFavori.FavoriToFavoriDto(f)).collect(Collectors.toList()));
            utilisateurRgpdDto.setCommune(utilisateur.getCommune());
            utilisateurRgpdDto.setCompteUtilisateur(utilisateur.getCompteUtilisateur());
            return utilisateurRgpdDto;
        } else {
            throw new CreationUtilisateurRgpdDtoImpossibleException();
        }
    }
}
