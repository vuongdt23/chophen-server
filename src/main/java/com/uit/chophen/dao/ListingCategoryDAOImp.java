package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uit.chophen.entities.ListingCategory;

@Repository
public class ListingCategoryDAOImp implements ListingCategoryDAO{

	
	private EntityManager entityManager;

	@Autowired
	public ListingCategoryDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	@Override
	public ListingCategory getListingCategoryById(int listingCategoryId) {
		Session session = entityManager.unwrap(Session.class);
		ListingCategory listingCategory = session.find(ListingCategory.class, listingCategoryId);
		return listingCategory;
	}


	@Override
	public List<ListingCategory> getAll() {
		Session session = entityManager.unwrap(Session.class);
		String query = "from ListingCategory";
		List<ListingCategory> categories = session.createQuery(query).getResultList();
		return categories;
	}


	@Override
	public ListingCategory save(ListingCategory listingCategory) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(listingCategory);
		return listingCategory;
	}


	@Override
	public long getCount() {
		Session session = entityManager.unwrap(Session.class);
		String countQ = "Select count(*) from ListingCategory l";
		Query query = session.createQuery(countQ);
		long count = (long) query.uniqueResult();
		return count;
	}

}
