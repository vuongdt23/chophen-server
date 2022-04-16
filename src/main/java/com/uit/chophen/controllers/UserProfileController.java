package com.uit.chophen.controllers;

import static com.uit.chophen.utils.SecurityConstant.*;

import javax.mail.MessagingException;
import static com.uit.chophen.utils.SecurityConstant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.EmailNotFoundException;
import com.uit.chophen.exception.ExceptionHandling;
import com.uit.chophen.exception.UserNotFoundException;
import com.uit.chophen.security.UserPrincipal;
import com.uit.chophen.services.UserProfileService;
import com.uit.chophen.utils.HttpResponse;
import com.uit.chophen.utils.JWTTokenProvider;

@RestController
@RequestMapping(value = "/user")
public class UserProfileController extends ExceptionHandling {

	
	private UserProfileService userProfileService;
	private AuthenticationManager authenticationManager; 
	private JWTTokenProvider jwtTokenProvider; 

	@PostMapping("/signUp")
	public ResponseEntity<UserProfile> signUp(@RequestBody UserProfile user) throws UserNotFoundException, AccountExistsException, EmailExistsException {
		UserProfile userProfile = userProfileService.signUp(user.getUserAddress(), user.getUserEmail(), user.getUserFullName(),user.getUserPhone(), user.getAccountName(), user.getPassword());
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<UserProfile> login(@RequestBody UserProfile user) {
		authenticate(user.getAccountName(), user.getPassword());
		UserProfile loginUser = userProfileService.findUserbyAccoutname(user.getAccountName());
		UserPrincipal principal = new  UserPrincipal(loginUser);
		HttpHeaders jwtHeaders = getJwtHeader(principal);
		return new ResponseEntity<>(loginUser, jwtHeaders, HttpStatus.OK);
	}
	
	 @GetMapping("/resetPassword/{email}")
	    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws MessagingException, EmailNotFoundException {
		 userProfileService.resetPassword(email);
	        return response(HttpStatus.OK, EMAIL_SENT + email);
	    }

	 private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
	        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
	                message), httpStatus);
	    }
	
	
	@Autowired
	public UserProfileController(UserProfileService userProfileService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider) {
		this.userProfileService = userProfileService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	

	private HttpHeaders getJwtHeader(UserPrincipal principal) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(principal));
		return headers;
	}
	private void authenticate(String accountName, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
		
	}
	@GetMapping("/find/{username}")
    public ResponseEntity<UserProfile> getUser(@PathVariable("username") String username) {
        UserProfile user = userProfileService.findUserbyAccoutname(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
}
