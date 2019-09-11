package fr.diginamic.entites;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private ZonedDateTime dateInscription;
	private Boolean notificationMeteo;
	private Boolean notificationPollution;

	@PrePersist
	public void prepersist() {
		this.dateInscription = ZonedDateTime.now();
	}

}
