package com.co.mundoviajero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class MundoViajeroService {

	public static void main(String[] args) {
		//SpringApplication.run(MundoViajeroService.class, args);
		new SpringApplication(MundoViajeroService.class).run(args);
	}

}
