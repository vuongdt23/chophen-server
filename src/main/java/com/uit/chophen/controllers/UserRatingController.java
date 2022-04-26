package com.uit.chophen.controllers;

import static com.uit.chophen.utils.SecurityConstant.TOKEN_PREFIX;

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

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;
import com.uit.chophen.httpdomains.request.RateUserRequestBody;
import com.uit.chophen.httpdomains.response.GetRatableResponseBody;
import com.uit.chophen.services.UserProfileService;
import com.uit.chophen.services.UserRatingService;
import com.uit.chophen.utils.JWTTokenProvider;

import io.grpc.ManagedChannelProvider.NewChannelBuilderResult;

@RestController
@RequestMapping(value = "ratings")

public class UserRatingController {
	private JWTTokenProvider jwtTokenProvider;
	private UserRatingService userRatingService;
	private UserProfileService userProfileService;

	@Autowired
	public UserRatingController(JWTTokenProvider jwtTokenProvider, UserRatingService userRatingService,
			UserProfileService userProfileService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRatingService = userRatingService;
		this.userProfileService = userProfileService;
	}

	@PostMapping("/rate/{userId}")
	public ResponseEntity<UserRating> rateUser(@PathVariable("userId") int userId,
			@RequestHeader(name = "Authorization") String token, @RequestBody RateUserRequestBody reqBody) {
		// LOGGER.info(token);
		UserProfile target = userProfileService.findUserbyId(userId);

		String creatorId = jwtTokenProvider.getSubjectFromToken(token.substring(TOKEN_PREFIX.length()));

		UserProfile creator = userProfileService.findUserbyId(Integer.parseInt(creatorId));

		UserRating newRating = userRatingService.createRating(creator, target, reqBody.getUserRatingPoint());

		// rating.setUserRatingTimestamp(new Date());hj

		return new ResponseEntity<>(newRating, HttpStatus.OK);
	}
	
	@GetMapping("/getRatable/{userId}")
	public ResponseEntity<GetRatableResponseBody> getRatable(@PathVariable("userId") int userId, @RequestHeader(name = "Authorization") String jwtToken){
		UserProfile target = userProfileService.findUserbyId(userId);
		String creatorId = jwtTokenProvider.getSubjectFromToken(jwtToken.substring(TOKEN_PREFIX.length()));
		UserProfile creator = userProfileService.findUserbyId(Integer.parseInt(creatorId));

		if(userRatingService.checkUsersRatingExists(creator, target) || Integer.parseInt(creatorId) == userId) {
			return new ResponseEntity<GetRatableResponseBody> (new GetRatableResponseBody(false), HttpStatus.OK);
		};
		return new ResponseEntity<GetRatableResponseBody>(new GetRatableResponseBody(true), HttpStatus.OK);
		

	}
}
