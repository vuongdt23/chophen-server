package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.uit.chophen.entities.UserProfile;

@Repository
public class UserProfileDAOImp implements UserProfileDAO {

	private EntityManager entityManager;

	@Autowired
	public UserProfileDAOImp(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<UserProfile> getAllUserProfiles() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<UserProfile> query = currentSession.createQuery("from UserProfile", UserProfile.class);
		List<UserProfile> userProfiles = query.getResultList();
		return userProfiles;
	}

}
