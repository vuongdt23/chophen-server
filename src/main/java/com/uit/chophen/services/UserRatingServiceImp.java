package com.uit.chophen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.dao.UserRatingDAO;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UserRatingServiceImp implements UserRatingService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private UserRatingDAO userRatingDAO;

	@Autowired
	public UserRatingServiceImp(UserRatingDAO userRatingDAO) {
		this.userRatingDAO = userRatingDAO;
	}

	@Override
	@Transactional
	public UserRating createRating(UserProfile creator, UserProfile target, int score) {
		
		UserRating newRating = new UserRating();
		newRating.setCreator(creator);
		newRating.setTarget(target);
		newRating.setUserRatingPoint(score);
		userRatingDAO.save(newRating);
		LOGGER.info("RATING CREATED FOR USER " + newRating.getTarget().getUserId() + " by User "
				+ newRating.getCreator().getUserId() + " valued " + newRating.getUserRatingPoint() + " at "
				+ newRating.getUserRatingTimestamp());
		return newRating;
	}

	@Override
	@Transactional
	public boolean checkUsersRatingExists(UserProfile creator, UserProfile target) {
		UserRating userRating = userRatingDAO.getUserRatingByCreatorAndTarget(creator, target);
		if(userRating!= null) return true;
		return false;
		
	}

}
