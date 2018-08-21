package com.co.mundoviajero.controller.impl;

import org.springframework.stereotype.Controller;

import com.co.mundoviajero.controller.Example;

@Controller
public class ExampleImpl implements Example{

	public String home() {
		return "Hello World! PI";
	}

}
