package com.co.mundoviajero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MundoViajeroService {

	public static void main(String[] args) {
		//SpringApplication.run(MundoViajeroService.class, args);
		new SpringApplication(MundoViajeroService.class).run(args);
	}

}
