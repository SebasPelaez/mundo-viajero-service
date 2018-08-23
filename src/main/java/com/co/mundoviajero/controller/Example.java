package com.co.mundoviajero.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Example {
	
	@RequestMapping("/example")
	public String home();
	
}
