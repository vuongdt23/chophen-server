package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uit.chophen.entities.University;

@Repository
public class UniversityDAOImp implements UniversityDAO {

	private EntityManager entityManager;

	@Autowired
	public UniversityDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	@Override
	public University save(University uni) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(uni);
		return uni;
	}
	@Override
	public long getCount() {
		Session session = entityManager.unwrap(Session.class);
		String countQ = "Select count(*) from University u";
		Query query = session.createQuery(countQ);
		long count = (long) query.uniqueResult();
		return count;
	}
	@Override
	public List<University> getAll() {
		Session session = entityManager.unwrap(Session.class);
		String selectQ = "from University";
		Query query = session.createQuery(selectQ, University.class);
		

		return query.getResultList();
	}
	@Override
	public University getById(int uniId) {
		Session session = entityManager.unwrap(Session.class);

		return session.get(University.class, uniId);
	}
	
	
}
