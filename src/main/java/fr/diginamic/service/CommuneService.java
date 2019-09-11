package fr.diginamic.service;

import fr.diginamic.entites.Commune;
import fr.diginamic.exception.CommuneNonTrouveeException;
import fr.diginamic.repository.CommuneRepository;
import org.springframework.stereotype.Service;

@Service
public class CommuneService {

    private CommuneRepository communeRepository;

    public CommuneService(CommuneRepository communeRepository) {
        this.communeRepository = communeRepository;
    }

    public Commune trouverCommuneParCode(String codeCommune){
        return communeRepository.findByCodeCommune(codeCommune).orElseThrow(CommuneNonTrouveeException::new);
    }
}
