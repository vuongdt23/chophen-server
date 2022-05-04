package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.UserProfile;

@Repository
public class ListingDAOImp implements ListingDAO {

	private EntityManager entityManager;

	@Autowired
	public ListingDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	@Transactional
	public void saves(Listing listing) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(listing);
		
	}


	@Override
	public Listing getListingById(int listingId) {
		Session session = entityManager.unwrap(Session.class);
		Listing listing = session.get(Listing.class, listingId);
		
		return listing;
	}


	@Override
	public List<Listing> getListingByUser(int userId) {
		Session session = entityManager.unwrap(Session.class);
		UserProfile user = session.get(UserProfile.class, userId);
		
		String query = "from Listing l where l.userProfile = :user";
		List<Listing> listings = session.createQuery(query).setParameter("user", user).getResultList();
		
		return listings;
	}

}
