package com.uit.chophen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.ExceptionHandling;
import com.uit.chophen.exception.UserNotFoundException;
import com.uit.chophen.services.UserProfileService;

@RestController
@RequestMapping(value = "/user")
public class UserProfileController extends ExceptionHandling {

	
	private UserProfileService userProfileService;

	@PostMapping("/signUp")
	public ResponseEntity<UserProfile> signUp(@RequestBody UserProfile user) throws UserNotFoundException, AccountExistsException, EmailExistsException {
		UserProfile userProfile = userProfileService.signUp(user.getUserAddress(), user.getUserEmail(), user.getUserFullName(),user.getUserPhone(), user.getAccountName(), user.getPassword());
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}
	
	public UserProfileService getUserProfileService() {
		return userProfileService;
	}
	@Autowired
	public UserProfileController(UserProfileService userProfileService) {
		super();
		this.userProfileService = userProfileService;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

}
