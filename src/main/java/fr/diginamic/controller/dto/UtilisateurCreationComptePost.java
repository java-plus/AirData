package fr.diginamic.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurCreationComptePost {

    @NotBlank
    private String identifiant;
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=\\S+$).{8,}$")
    private String motDePasse;
    @Email
    private String email;
    @Positive
    @NotNull
    private Integer age;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    private String codeCommune;


}
