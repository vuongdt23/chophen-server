package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;
import com.uit.chophen.entities.UserSavedListing;

@Repository
public class SavedListingDAOImp implements SavedListingDAO {
    

	private EntityManager entityManager;

	@Autowired
	public SavedListingDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserSavedListing getSavedListingByUserAndListing(UserProfile user, Listing listing) {
		Session session = entityManager.unwrap(Session.class);
		String query = "from UserSavedListing u where u.listing = :listing and u.userProfile = :user";

		UserSavedListing result = (UserSavedListing) session.createQuery(query).setParameter("listing", listing).setParameter("user", user).getResultStream().findFirst().orElse(null);
		return result;
	}

	@Override
	public UserSavedListing save(UserSavedListing userSavedListing) {
		Session session = entityManager.unwrap(Session.class);
		session.save(userSavedListing);
		return userSavedListing;
	}

	@Override
	public void remove(UserSavedListing userSavedListing) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(userSavedListing);
	}

	@Override
	public List<Listing> getSaveListingsByUser(UserProfile user) {
		Session session = entityManager.unwrap(Session.class);

		String query = "select u.listing from UserSavedListing u where u.userProfile = :user";
		List<Listing> result = session.createQuery(query).setParameter("user", user).getResultList();
		return result;
	};
}
