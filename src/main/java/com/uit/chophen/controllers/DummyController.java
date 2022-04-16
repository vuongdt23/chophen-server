package com.uit.chophen.controllers;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.services.EmailService;
import com.uit.chophen.utils.*;
@RestController
public class DummyController {

	@Autowired
	private JWTTokenProvider provider;
	@Autowired
	private EmailService emailService;
	@GetMapping("/emailTest")
	public String sayHello() {
		try {
			emailService.sendNewPasswordEmail("Vuong", "123456", "vuong.dt.23@gmail.com");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return provider.getSecret();
	}
	
	
}
