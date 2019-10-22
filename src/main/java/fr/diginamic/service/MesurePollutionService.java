package fr.diginamic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesurePollution;
import fr.diginamic.repository.MesurePollutionRepository;

/**
 * class MesurePollutionService cette classe fait principalement des appels et
 * des insertions en base grâce au MesurePollutionRepository Elle est
 * principalement utilisée par des controlleurs
 * 
 * @author Diginamic02
 *
 */
@Service
public class MesurePollutionService {

	MesurePollutionRepository mesurePollutionRepository;

	/**
	 * Constructeur renvoie une instance de MesurePollutionService (sous la
	 * forme d'un singleton) avec comme attribut un mesurePollutionRepository
	 * 
	 * @param mesurePollutionRepository
	 */
	public MesurePollutionService(MesurePollutionRepository mesurePollutionRepository) {
		super();
		this.mesurePollutionRepository = mesurePollutionRepository;

	}

	/**
	 * Methode void insererEnBase(List<MesurePollution> ListeDeMesures) Cette
	 * methode permet d'inserer en base de donnée une liste d'objets
	 * MesurePollution
	 * 
	 * @param ListeDeMesures
	 */
	public void insererEnBase(List<MesurePollution> ListeDeMesures) {
		// ListeDeMesures.removeAll(Collections.singleton(null));
		mesurePollutionRepository.saveAll(ListeDeMesures);
	}

	/**
	 * Methode List<MesurePollution> obtenirLesMesuresDePollution(String
	 * codeCommune) Cette methode permet de d'obtenir toutes les MesurePollution
	 * présentes en basse de donnée et dont le CodeCommune correspond
	 * 
	 * @param codeCommune
	 * @return
	 */
	public List<MesurePollution> obtenirLesMesuresDePollution(String codeCommune) {
		return mesurePollutionRepository.obtenirLesMesuresDePollution(codeCommune);
	}

	/**
	 * Methode MesurePollution obtenirlaMesureDePollutionPm10(String
	 * codeCommune) Cette methode permet de d'obtenir la dernière
	 * MesurePollution liée au PM10 présente en basse de donnée et dont le
	 * CodeCommune correspond
	 * 
	 * @param codeCommune
	 * @return
	 */
	public MesurePollution obtenirlaMesureDePollutionPm10(String codeCommune) {
		return mesurePollutionRepository.obtenirLaMesureDePM10(codeCommune).get(0);
	}

	/**
	 * Methode Optional<MesurePollution> obtenirMesurePollution(MesurePollution
	 * mesurePollution) cette methode permet d'obtenir une MesurePollution
	 * présente en base de donnée dont l'id correspond
	 * 
	 * @param mesurePollution
	 * @return
	 */
	public Optional<MesurePollution> obtenirMesurePollution(MesurePollution mesurePollution) {
		return mesurePollutionRepository.findById(mesurePollution.getId());
	}

	/**
	 * Methode void mettreEnBaseMesurePollution(MesurePollution mesurePollution)
	 * Cette methode permet d'inserer en base de donnée une MesurePollution
	 * 
	 * @param mesurePollution
	 */
	public void mettreEnBaseMesurePollution(MesurePollution mesurePollution) {
		mesurePollutionRepository.save(mesurePollution);
	}

}
