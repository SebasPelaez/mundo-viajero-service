package com.co.mundoviajero.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Example {
	
	@RequestMapping("/example")
	public String home() {
		return "Hello World! PI";
	}
}
