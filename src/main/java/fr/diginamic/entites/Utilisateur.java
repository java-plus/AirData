package fr.diginamic.entites;

import fr.diginamic.controller.dto.UtilisateurCreationComptePost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * represente un utilisateur
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    /**
     * id de l’utilisateur
     */
    @Id
    private String id;

    /**
     * liste des roles de l’utilisateur
     */
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Role> role;

    /**
     * identifiant de l’utilisateur
     */
    @NotBlank
    @Column(unique = true)
    private String identifiant;
    /**
     * mot de passe de l’utilisateur, doit contenir au minimum une majuscule, une minuscule, un chiffre, un caractere spécial et une longueur de 8
     */
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=\\S+$).{8,}$")
    private String motDePasse;
    /**
     * email de l’utilisateur
     */
    @Email
    private String email;
    /**
     * age de l’utilisateur
     */
    @Positive
    private Integer age;

    /**
     * liste des favoris de l’utilisateur
     */
    @OneToMany(mappedBy = "utilisateur")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Favori> listeFavori;

    /**
     * commune de l’utilisateur
     */
    @ManyToOne
    Commune commune;

    /**
     * compte de l’utilisateur
     */
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    CompteUtilisateur compteUtilisateur;


    /**
     * attribut un identifiant UUID à l’utilisateur
     */
    @PrePersist
    private void prepersist(){
        this.id = UUID.randomUUID().toString();
    }
}
