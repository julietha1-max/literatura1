package com.desafioliteratura.repository;
import com.desafioliteratura.repository.AutorRepository;
import com.desafioliteratura.repository.LibroRepository;

import com.desafioliteratura.literatura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan(basePackages = {"com.desafioliteratura.literatura.model", "com.desafioliteratura.repository"})

public class LiteraturaApplication implements CommandLineRunner {
	private LibroRepository libroRepository;
	private AutorRepository autorRepository;
@Autowired
public LiteraturaApplication(LibroRepository libroRepository, AutorRepository autorRepository){
	this.libroRepository=libroRepository;
	this.autorRepository=autorRepository;
}

	public static void main(String[] args) {

		SpringApplication.run(LiteraturaApplication.class, args);
	}
	@Override

	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();
	}






	}

