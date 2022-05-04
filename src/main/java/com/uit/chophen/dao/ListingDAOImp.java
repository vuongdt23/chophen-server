package com.uit.chophen.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.entities.Listing;

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

}
