package com.uit.chophen.controllers;

import java.util.List;
import static com.uit.chophen.utils.SecurityConstant.*;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import static com.uit.chophen.utils.SecurityConstant.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;
import com.uit.chophen.exception.AccountExistsException;
import com.uit.chophen.exception.EmailExistsException;
import com.uit.chophen.exception.EmailNotFoundException;
import com.uit.chophen.exception.ExceptionHandling;
import com.uit.chophen.exception.UserNotFoundException;
import com.uit.chophen.httpdomains.request.LoginRequestBody;
import com.uit.chophen.httpdomains.request.RateUserRequestBody;
import com.uit.chophen.httpdomains.request.SignUpRequestBody;
import com.uit.chophen.httpdomains.request.UpdateProfileRequestBody;
import com.uit.chophen.httpdomains.response.LoginSucessResponseBody;
import com.uit.chophen.security.UserPrincipal;
import com.uit.chophen.services.UserProfileService;
import com.uit.chophen.services.UserRatingService;
import com.uit.chophen.utils.HttpResponse;
import com.uit.chophen.utils.JWTTokenProvider;

@RestController
@RequestMapping(value = "/user")
public class UserProfileController extends ExceptionHandling {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private UserProfileService userProfileService;
	private AuthenticationManager authenticationManager;
	private JWTTokenProvider jwtTokenProvider;
	private UserRatingService userRatingService;

	@PostMapping("/signUp")
	public ResponseEntity<UserProfile> signUp(@RequestBody SignUpRequestBody reqBody)
			throws UserNotFoundException, AccountExistsException, EmailExistsException {
		UserProfile userProfile = userProfileService.signUp(reqBody.getUserAddress(), reqBody.getUserEmail(),
				reqBody.getUserFullName(), reqBody.getUserPhone(), reqBody.getAccountName(), reqBody.getPassword());
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginSucessResponseBody> login(@RequestBody LoginRequestBody user) {
		authenticate(user.getAccountName(), user.getPassword());
		UserProfile loginUser = userProfileService.findUserbyAccoutname(user.getAccountName());
		UserPrincipal principal = new UserPrincipal(loginUser);
		String token = jwtTokenProvider.generateJwtToken(principal);
		LoginSucessResponseBody resBody = new LoginSucessResponseBody(token);
		HttpHeaders jwtHeaders = getJwtHeader(principal);
		return new ResponseEntity<LoginSucessResponseBody>(resBody, jwtHeaders, HttpStatus.OK);
	}

	@PostMapping("/resetPassword/{email}")
	public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email)
			throws MessagingException, EmailNotFoundException {
		userProfileService.resetPassword(email);
		return response(HttpStatus.OK, EMAIL_SENT + email);
	}

	private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(
				new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message),
				httpStatus);
	}

	@PostMapping("/updateProfilePic")
	public ResponseEntity<UserProfile> updateProfilePic(@RequestHeader(name = "Authorization") String jwtToken,
			@RequestPart("file") MultipartFile file) throws IOException {

		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));

		return new ResponseEntity<UserProfile>(userProfileService.updateProfilePic(userId, file), HttpStatus.OK);

	}

	@Autowired
	public UserProfileController(UserProfileService userProfileService, AuthenticationManager authenticationManager,
			JWTTokenProvider jwtTokenProvider, UserRatingService userRatingService) {
		this.userProfileService = userProfileService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRatingService = userRatingService;
	}

	private HttpHeaders getJwtHeader(UserPrincipal principal) {
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
	@GetMapping("/findById/{userId}")
	public ResponseEntity<UserProfile> getUserById(@PathVariable("userId") int userId) {
		UserProfile user = userProfileService.findUserbyId(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<UserProfile>updateProfile(@RequestBody UpdateProfileRequestBody reqBody,@RequestHeader(name = "Authorization") String jwtToken){
		int userId = Integer.parseInt(jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length())));
		
		UserProfile userProfile = userProfileService.updateProfile(userId, reqBody.getUserAddress(), reqBody.getUserFullName(), reqBody.getUserPhone());
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
		
	}
	

}
