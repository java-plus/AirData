package fr.diginamic.controller;

import fr.diginamic.entites.Alerte;
import fr.diginamic.service.AlerteService;
import fr.diginamic.service.IAlerteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class AlerteController {

private IAlerteService alerteService;

    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @PostMapping("/alerte")
    public Alerte creerAlerte( @Valid @RequestBody Alerte alerte){
        return alerteService.creerAlerte(alerte);
    }

    @GetMapping("/alertes")
    public List<Alerte> recupererAlertes(@RequestBody Alerte alerte){
        return alerteService.recupererAlerte(alerte);
    }
}
