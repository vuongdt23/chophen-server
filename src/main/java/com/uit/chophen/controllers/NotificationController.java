package com.uit.chophen.controllers;

import static com.uit.chophen.utils.SecurityConstant.TOKEN_PREFIX;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.httpdomains.response.BasicBooleanResponseBody;
import com.uit.chophen.httpdomains.response.BasicStringResponseBody;
import com.uit.chophen.httpdomains.response.GetNotificationsResponseBody;
import com.uit.chophen.services.NotificationService;
import com.uit.chophen.utils.JWTTokenProvider;
import com.uit.chophen.entities.UserNotification;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {

	private NotificationService notificationService;
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	public NotificationController(NotificationService notificationService, JWTTokenProvider jwtTokenProvider) {
		super();
		this.notificationService = notificationService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@GetMapping("/myNotification")
	public ResponseEntity<GetNotificationsResponseBody> getUserNotifications(
			@RequestHeader(name = "Authorization") String jwtToken) {
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		List<UserNotification> notificationList = notificationService.getUserNotifications(userId);
		GetNotificationsResponseBody responseBody = new GetNotificationsResponseBody();
		responseBody.setNotificationList(notificationList);

		return new ResponseEntity<GetNotificationsResponseBody>(responseBody, HttpStatus.OK);

	}
	
	@PostMapping("/markNotificationAsRead/{notificationId}")
	public ResponseEntity<BasicStringResponseBody> markNotificationAsRead(@PathVariable int notificationId, 	@RequestHeader(name = "Authorization") String jwtToken){
		BasicStringResponseBody responseBody = new BasicStringResponseBody();
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		if(!notificationService.checkOwnership(userId, notificationId)) {
			responseBody.setMessage("You are not allowed to update this content");
			return new ResponseEntity<BasicStringResponseBody>(responseBody, HttpStatus.FORBIDDEN);

		}
		if(notificationService.checkIsRead(notificationId)) {
			responseBody.setMessage("The Notification is already read");
			return new ResponseEntity<BasicStringResponseBody>(responseBody, HttpStatus.BAD_REQUEST);

		}
		notificationService.setNotificationAsRead(notificationId);
		responseBody.setMessage("Mark Notification As Read Sucessfully");
		return new ResponseEntity<BasicStringResponseBody>(responseBody, HttpStatus.OK);
		
	}
	
	@PostMapping("/markAllAsRead")
	public ResponseEntity<BasicStringResponseBody> markAllAsRead(@RequestHeader(name = "Authorization") String jwtToken){
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		notificationService.setAllUserNotificationAsRead(userId);
		BasicStringResponseBody responseBody = new BasicStringResponseBody();
		responseBody.setMessage("Set all Notification As Read");
		notificationService.setAllUserNotificationAsRead(userId);
		return new ResponseEntity<BasicStringResponseBody>(responseBody, HttpStatus.OK);
	}
	
	@GetMapping("/hasUnread")
	public ResponseEntity<BasicBooleanResponseBody> hasNewNotifications(@RequestHeader(name = "Authorization") String jwtToken){
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		BasicBooleanResponseBody responseBody = new BasicBooleanResponseBody();
		responseBody.setValue(notificationService.checkHasUnreadNotifications(userId));
		return new ResponseEntity<BasicBooleanResponseBody>(responseBody, HttpStatus.OK);
	}
}
