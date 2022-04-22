package com.uit.chophen.controllers;

import static com.uit.chophen.utils.SecurityConstant.TOKEN_PREFIX;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.fcmdomain.FCMTokenStoreObj;
import com.uit.chophen.httpdomains.request.RegisterFCMDeviceRequestBody;
import com.uit.chophen.services.PushNotificationService;
import com.uit.chophen.utils.JWTTokenProvider;

@RestController
@RequestMapping(value = "/pushNotifications")
public class PushNotificationController {

	private PushNotificationService pushNotificationService;
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	public PushNotificationController(PushNotificationService pushNotificationService,
			JWTTokenProvider jwtTokenProvider) {
		this.pushNotificationService = pushNotificationService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@PostMapping("/registerDevice")
	public ResponseEntity<String> registerDevice(@RequestBody RegisterFCMDeviceRequestBody requestBody,
			@RequestHeader(name = "Authorization") String jwtToken) {
		String fcmToken = requestBody.getFcmToken();
		int userId =Integer.parseInt( jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		
		pushNotificationService.registerDevice(fcmToken, userId);
		try {
			pushNotificationService.sendMessageToToken(fcmToken);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Your device has been successfully registered", HttpStatus.OK);

	}
	
	@GetMapping("/test")
	public List<FCMTokenStoreObj> test() throws InterruptedException, ExecutionException{
		return pushNotificationService.getFCMTokensByUserId(1);
	}

}
