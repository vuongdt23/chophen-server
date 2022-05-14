package com.uit.chophen.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.entities.Listing;
import com.uit.chophen.entities.ListingCategory;
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


	@Override
	public int getAllListingsCount() {
		Session session = entityManager.unwrap(Session.class);
		String countQ = "Select count (l.id) from Listing l";
		Query countQuery = session.createQuery(countQ);
		int countResult = (int) countQuery.uniqueResult();
		return countResult;

	}




	@Override
	public long getListingCountByCategories(List<ListingCategory> categories) {
		List<Integer> catStrings = new ArrayList<Integer>();
		for(ListingCategory lc: categories) {
			catStrings.add(lc.getListingCategoryId());
		}
		
		Session session = entityManager.unwrap(Session.class);
		String countQ = "Select count (l.id) from Listing l join l.listingCategories lc where lc.listingCategoryId in :categories";
		Query countQuery = session.createQuery(countQ).setParameter("categories", catStrings);
		long countResult = (long) countQuery.uniqueResult();
		return countResult;

	}


	@Override
	public List<Listing> getListingByCategories(int firstResult, int lastResult, List<ListingCategory> categories) {
		List<Integer> catStrings = new ArrayList<Integer>();
		for(ListingCategory lc: categories) {
			catStrings.add(lc.getListingCategoryId());
		}
		
		Session session = entityManager.unwrap(Session.class);
		String selectQ = "from Listing l join l.listingCategories lc where lc.listingCategoryId in :categories ";
		Query selectQuery = session.createQuery(selectQ);
		selectQuery.setParameterList("categories", catStrings.toArray());
		selectQuery.setFirstResult(firstResult);
		selectQuery.setMaxResults(lastResult);
		List<Listing> listings = selectQuery.getResultList();
		return listings;
	}


	
	
	
	
	

}
