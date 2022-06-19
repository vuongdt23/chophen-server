package com.uit.chophen.controllers;

import static com.uit.chophen.utils.SecurityConstant.TOKEN_PREFIX;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.uit.chophen.fcmdomain.FCMTokenStoreObj;
import com.uit.chophen.httpdomains.request.RegisterFCMDeviceRequestBody;
import com.uit.chophen.httpdomains.response.BasicStringResponseBody;
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
	public ResponseEntity<BasicStringResponseBody> registerDevice(@RequestBody RegisterFCMDeviceRequestBody requestBody,
			@RequestHeader(name = "Authorization") String jwtToken) throws FirebaseMessagingException {
		String fcmToken = requestBody.getFcmToken();
		int userId =Integer.parseInt( jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new  BasicStringResponseBody();
		
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
		resBody.setMessage("Your device has been successfully registered");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.OK);

	}
	
	@PostMapping("/sendNewMessageNotification/{userId}")
	public ResponseEntity<BasicStringResponseBody> sendNewMessageNotifications(@PathVariable int receiveUserId, @RequestHeader(name = "Authorization") String jwtToken) throws FirebaseMessagingException, InterruptedException, ExecutionException{
		int sendUserId =Integer.parseInt( jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicStringResponseBody resBody = new  BasicStringResponseBody();
		pushNotificationService.sendNewMessageNotificationToUser(receiveUserId, sendUserId);
		resBody.setMessage("Send Notification successfully");
		return new ResponseEntity<BasicStringResponseBody>(resBody, HttpStatus.OK);
	}

	
}
