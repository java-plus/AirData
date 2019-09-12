package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String region;
    private String departement;


    @Pattern(regexp = "^[0-9]*$")
    private String codeCommune;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;
    private String message;
    @NotNull
    private ZonedDateTime dateDebut;
    @NotNull
    private ZonedDateTime dateFin;



}
