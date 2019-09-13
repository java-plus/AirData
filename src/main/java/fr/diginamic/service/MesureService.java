package fr.diginamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.repository.MesureRepository;

@Service
public class MesureService {

	@Autowired
	MesureRepository mesureRepository;

}
