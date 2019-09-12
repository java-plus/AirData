package fr.diginamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.service.InsertionEnBasDeDonneeService;

@SpringBootApplication
public class AirDataApplication {

	@Autowired
	InsertionEnBasDeDonneeService insertionEnBasDeDonneeService;

	public static void main(String[] args) {
		SpringApplication.run(AirDataApplication.class, args);

	}

}
