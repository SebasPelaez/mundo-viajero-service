package com.co.mundoviajero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.co.mundoviajero.controller.impl"})
public class MundoViajeroService {

	public static void main(String[] args) {
		SpringApplication.run(MundoViajeroService.class, args);
	}

}
