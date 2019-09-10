package fr.diginamic.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteUtilisateur {

    @Id
    @GeneratedValue

private ZonedDateTime dateInscription;
private Boolean notificationMeteo;
private Boolean notificationPollution;


@PrePersist
public void prepersist(){
    this.dateInscription = ZonedDateTime.now();
}


}
