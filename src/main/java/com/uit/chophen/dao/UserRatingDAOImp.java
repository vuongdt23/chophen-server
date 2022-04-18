package com.uit.chophen.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;

@Repository
public class UserRatingDAOImp implements UserRatingDAO{

	private EntityManager entityManager;

	@Autowired
	public UserRatingDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(UserRating userRating) {
		Session session = entityManager.unwrap(Session.class);
		session.save(userRating);

	}



	




}
