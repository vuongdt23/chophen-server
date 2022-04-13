package com.uit.chophen.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uit.chophen.utils.*;
@RestController
public class DummyController {

	private JWTTokenProvider provider;
	@GetMapping("/")
	public String sayHello() {
		return provider.getSecret();
	}
}
