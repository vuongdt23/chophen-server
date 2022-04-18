package com.uit.chophen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.dao.UserRatingDAO;
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
	public void createRating(UserRating userRating) {
		this.userRatingDAO.save(userRating);
		LOGGER.info("RATING CREATED FOR USER " + userRating.getTarget().getUserId() + " by User "
				+ userRating.getCreator().getUserId() + " valued " + userRating.getUserRatingPoint() + " at "
				+ userRating.getUserRatingTimestamp());
	}

}
