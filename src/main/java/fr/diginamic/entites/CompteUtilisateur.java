package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ZonedDateTime dateInscription;
    private Boolean notificationMeteo=false;
    private Boolean notificationPollution=false;


    @PrePersist
    public void prepersist() {
        this.dateInscription = ZonedDateTime.now();
    }


}
