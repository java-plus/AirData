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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    private String id;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Role> role;
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
    private Integer age;

    @OneToMany(mappedBy = "utilisateur")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Favori> listeFavori;

    @ManyToOne
    Commune commune;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    CompteUtilisateur compteUtilisateur;



    @PrePersist
    private void prepersist(){
        this.id = UUID.randomUUID().toString();
    }
}
