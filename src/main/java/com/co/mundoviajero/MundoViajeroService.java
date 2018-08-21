package com.co.mundoviajero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@SpringBootApplication
//@ComponentScan(basePackages = { "com.co.mundoviajero", "com.co.mundoviajero.controller" })

@Controller
@SpringBootApplication
public class MundoViajeroService {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(MundoViajeroService.class, args);
	}

}
