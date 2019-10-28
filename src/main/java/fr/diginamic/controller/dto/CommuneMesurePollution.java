package fr.diginamic.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommuneMesurePollution {

	private String codeCommune;
	private Double valeurPm10;
	private Double valeurPm25;
	private Double valeurNO2;
	private Double valeurSO2;
	private Double valeurCO;
	private Double valeurO3;

}
