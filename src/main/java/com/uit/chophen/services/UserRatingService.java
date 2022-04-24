package com.uit.chophen.services;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;

public interface UserRatingService {

	public UserRating createRating(UserProfile creator, UserProfile target, int score);
}
