package fr.diginamic.entites;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Utilisateur {

    @Id
    private String id;

    private String role;
    @NotBlank
    @Column(unique = true)
    private String identifiant;
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=\\S+$).{8,}$")
    private String motDePasse;
    @Email
    private String email;
    @Positive
    private Integer Age;

    @OneToMany(mappedBy = "utilisateur")
    List<Favori> listeFavori;

    @OneToOne
    Commune commune;

    @OneToOne
    CompteUtilisateur compteUtilisateur;

    @PrePersist
    private void prepersist(){
        this.id = UUID.randomUUID().toString();
    }
}
