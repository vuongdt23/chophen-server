package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
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

}
