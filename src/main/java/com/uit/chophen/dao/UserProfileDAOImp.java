package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.entities.UserProfile;

@Repository
public class UserProfileDAOImp implements UserProfileDAO {

	private EntityManager entityManager;

	@Autowired
	public UserProfileDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserProfile findUserProfileByAccountName(String accountName) {
		Session session = entityManager.unwrap(Session.class);

		UserProfile userProfile = (UserProfile) session
				.createQuery("select p from UserProfile p where p.accountName = :accountName")
				.setParameter("accountName", accountName).getResultStream().findFirst().orElse(null);
		return userProfile;
	}

	@Override
	public List<UserProfile> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(UserProfile userProfile) {
		Session session = entityManager.unwrap(Session.class);
		session.save(userProfile);

	}

	@Override
	public UserProfile findUserProfileByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);

		UserProfile userProfile = (UserProfile) session
				.createQuery("select p from UserProfile p where p.userEmail = :email").setParameter("email", email)
				.getResultStream().findFirst().orElse(null);
		return userProfile;
	}

	@Override
	public UserProfile findUserProfileById(int id) {
		Session session = entityManager.unwrap(Session.class);

		UserProfile userProfile = (UserProfile) session.createQuery("from UserProfile u where userId =:uId").setParameter("uId", id).getResultStream().findFirst().orElse(null);;
		
		return userProfile;
	}

	

}
