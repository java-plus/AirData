package fr.diginamic.service;

import fr.diginamic.entites.Alerte;
import fr.diginamic.exception.AlerteInvalideException;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.AlerteRepository;
import fr.diginamic.repository.CommuneRepository;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlerteService implements IAlerteService {

    private AlerteRepository alerteRepository;
    private CommuneRepository communeRepository;

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();



    public AlerteService(AlerteRepository alerteRepository, CommuneRepository communeRepository) {
        this.alerteRepository = alerteRepository;
        this.communeRepository = communeRepository;
    }

    @Override
    public Alerte creerAlerte(Alerte alerte){
        if(alerte!=null) {
            if (alerte.getCodeCommune() != null) {
                communeRepository.findByCodeCommune(alerte.getCodeCommune()).orElseThrow(CommuneNonTrouveeException::new);
            }

            if (validator.validate(alerte).isEmpty()) {
                alerteRepository.save(alerte);
            } else {
                throw new AlerteInvalideException();
            }
        }else{
            throw new AlerteInvalideException();
        }
        return alerte;
    }

    @Override
    public List<Alerte> recupererAlerte(Alerte alerte){
        List<Alerte> listeAlertes = new ArrayList<>();
        if(alerte.getRegion()!=null){
            listeAlertes = alerteRepository.findAlerteWithRegion(alerte.getType(),alerte.getRegion(), ZonedDateTime.now());
        }else if (alerte.getDepartement()!=null){
            listeAlertes = alerteRepository.findAlerteWithDepartement(alerte.getType(),alerte.getDepartement(), ZonedDateTime.now());
        }else if (alerte.getCodeCommune()!=null){
            listeAlertes = alerteRepository.findAlerteWithCodeCommune(alerte.getType(),alerte.getCodeCommune(), ZonedDateTime.now());
        }
        return listeAlertes;
    }
}
