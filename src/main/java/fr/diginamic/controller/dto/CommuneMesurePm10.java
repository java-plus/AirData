package fr.diginamic.controller.dto;

import fr.diginamic.entites.MesurePollution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommuneMesurePm10 {

	public CommuneMesurePm10(String codeCommune2, MesurePollution mesurePollutionPm10) {
		this.codeCommune = codeCommune2;
		this.valeurPm10 = mesurePollutionPm10.getValeur();
	}

	private String codeCommune;
	private Double valeurPm10;

}
