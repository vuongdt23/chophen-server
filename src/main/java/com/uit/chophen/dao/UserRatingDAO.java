package com.uit.chophen.dao;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;

public interface UserRatingDAO {

	

	public void save(UserRating userRating);
	
	public UserRating getUserRatingByCreatorAndTarget(UserProfile creator, UserProfile target);
	public long getUserLikeCount(int userId);
	public long getUserDisLikeCount(int userId);
}
