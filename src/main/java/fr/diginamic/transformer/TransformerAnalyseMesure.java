/**
 * 
 */
package fr.diginamic.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.diginamic.controller.dto.AnalyseMesureDto;
import fr.diginamic.controller.dto.AnalyseMesurePollutionDto;
import fr.diginamic.entites.MesurePollution;

/**
 * @author Eloi
 *
 */

@Component
public class TransformerAnalyseMesure {

	public List<AnalyseMesurePollutionDto> mesurePollutionToDto(List<MesurePollution> listePollution) {
		AnalyseMesureDto analyseMesureDto = new AnalyseMesureDto();
		AnalyseMesurePollutionDto analyseMesurePollutionDto = new AnalyseMesurePollutionDto();

		listePollution.get(0).getDate();
		listePollution.get(0).getValeur();

		return null;
	}

	public void mesureMeteoToAnalyseMesureDto() {

	}

}
