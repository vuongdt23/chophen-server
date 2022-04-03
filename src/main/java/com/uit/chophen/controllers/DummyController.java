package com.uit.chophen.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

	@GetMapping("/")
	public String sayHello() {
		return "Hello World";
	}
}
