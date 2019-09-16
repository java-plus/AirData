package fr.diginamic.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.MesureMeteo;
import fr.diginamic.repository.MesureMeteoRepository;

/**
 * class MesureMeteoService cette classe fait principalement des appels et des
 * insertions en base grâce au MesureMeteoRepository Elle est principalement
 * utilisée par des controlleurs
 * 
 * @author Diginamic02
 *
 */
@Service
public class MesureMeteoService {

	MesureMeteoRepository mesureMeteoRepository;

	/**
	 * Constructeur MesureMeteoService renvoie une instance de
	 * MesureMeteoService (sous la forme d'un singleton) avec comme attribut un
	 * mesureMeteoRepository
	 * 
	 * @param mesureMeteoRepository
	 */
	public MesureMeteoService(MesureMeteoRepository mesureMeteoRepository) {
		super();
		this.mesureMeteoRepository = mesureMeteoRepository;
	}

	/**
	 * Methode void insererEnBase(List<MesureMeteo> ListeDeMesures) Cette
	 * methode permet d'inserer en base de donnée une liste d'objets MesureMeteo
	 * 
	 * @param ListeDeMesures
	 */
	public void insererEnBase(List<MesureMeteo> ListeDeMesures) {
		// ListeDeMesures.removeAll(Collections.singleton(null));
		mesureMeteoRepository.saveAll(ListeDeMesures);
	}

	/**
	 * Methode List<MesureMeteo> obtenirLesMesuresDeMeteo(String codeCommune)
	 * Cette methode permet de d'obtenir toutes les MesuresMeteo présentes en
	 * basse de donnée et dont le CodeCommune correspond
	 * 
	 * @param codeCommune
	 * @return
	 */
	public MesureMeteo obtenirLesMesuresDeMeteo(String codeCommune) {
		// TODO Auto-generated method stub

		List<MesureMeteo> listeDeMesureMeteo = mesureMeteoRepository.obtenirLesMesuresDeMeteo(codeCommune);
		MesureMeteo mesureLaPlusRecente = new MesureMeteo();
		ZonedDateTime dateMesureLaPlusRecente = ZonedDateTime.now().minusYears(30);
		for (MesureMeteo mesureMeteo : listeDeMesureMeteo) {
			if (mesureMeteo.getDate().isAfter(dateMesureLaPlusRecente)) {
				mesureLaPlusRecente = mesureMeteo;
				dateMesureLaPlusRecente = mesureMeteo.getDate();
			}
		}

		return mesureLaPlusRecente;
	}

	/**
	 * Methode Optional<MesureMeteo> obtenirMesureMeteo(MesureMeteo mesureMeteo)
	 * cette methode permet d'obtenir une MesureMeteo présente en base de donnée
	 * dont l'id correspond
	 * 
	 * @param mesureMeteo
	 * @return
	 */
	public Optional<MesureMeteo> obtenirMesureMeteo(MesureMeteo mesureMeteo) {
		// TODO Auto-generated method stub
		return mesureMeteoRepository.findById(mesureMeteo.getId());
	}

	/**
	 * Methode void mettreEnBaseMesureMeteo(MesureMeteo mesureMeteo) Cette
	 * methode permet d'inserer en base de donnée une MesureMeteo
	 * 
	 * @param mesureMeteo
	 */
	public void mettreEnBaseMesureMeteo(MesureMeteo mesureMeteo) {
		// TODO Auto-generated method stub
		mesureMeteoRepository.save(mesureMeteo);
	}

}
