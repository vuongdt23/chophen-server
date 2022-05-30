package com.uit.chophen.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uit.chophen.entities.ListingStatus;

@Repository
public class ListingStatusDAOImp implements ListingStatusDAO {

	private EntityManager entityManager;

	@Autowired
	public ListingStatusDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public ListingStatus save(ListingStatus status) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(status);
		return status;
	}

	@Override
	public long getCount() {
		Session session = entityManager.unwrap(Session.class);
		String countQ = "Select count(*) from ListingStatus l";
		Query query = session.createQuery(countQ);
		long count = (long) query.uniqueResult();
		return count;
	}

	@Override
	public ListingStatus getListingStatusById(int listingStatusId) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(ListingStatus.class, listingStatusId);
	}

}
