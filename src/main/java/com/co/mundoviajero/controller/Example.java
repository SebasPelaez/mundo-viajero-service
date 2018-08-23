package com.co.mundoviajero.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/example")
public interface Example {
	
	@RequestMapping("/example2")
	public String home();
	
}
