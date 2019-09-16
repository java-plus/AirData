package fr.diginamic.service;

import fr.diginamic.entites.Alerte;
import fr.diginamic.entites.Commune;
import fr.diginamic.entites.Type;
import fr.diginamic.exception.AlerteInvalideException;
import fr.diginamic.repository.AlerteRepository;
import fr.diginamic.repository.CommuneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


public class AlerteServiceTest {

    private AlerteService alerteService;

    @Test(expected = AlerteInvalideException.class)
    public void test_creerAlerte_alerteNull(){
       AlerteRepository mockAlerteRepository = Mockito.mock(AlerteRepository.class);
        Mockito.when(mockAlerteRepository.save(Mockito.any(Alerte.class))).thenReturn(null);
        CommuneRepository mockCommuneRepository = Mockito.mock(CommuneRepository.class);
        Mockito.when(mockCommuneRepository.findByCodeCommune(Mockito.anyString())).thenReturn(java.util.Optional.of(new Commune()));

        this.alerteService = new AlerteService(mockAlerteRepository,mockCommuneRepository);
        alerteService.creerAlerte(null);
    }

    @Test(expected = AlerteInvalideException.class)
    public void test_creerAlerte_alerteCodeCommuneInconnu(){
        Alerte alerte= new Alerte(null,null,null,"1", Type.METEO,"message", ZonedDateTime.now(),ZonedDateTime.now().plusDays(1));
        AlerteRepository mockAlerteRepository = Mockito.mock(AlerteRepository.class);
        Mockito.when(mockAlerteRepository.save(Mockito.any(Alerte.class))).thenReturn(alerte);
        CommuneRepository mockCommuneRepository = Mockito.mock(CommuneRepository.class);
        Mockito.when(mockCommuneRepository.findByCodeCommune(Mockito.anyString())).thenReturn(java.util.Optional.of(new Commune()));

        this.alerteService = new AlerteService(mockAlerteRepository,mockCommuneRepository);
        alerteService.creerAlerte(null);
    }


}
