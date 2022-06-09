package com.uit.chophen.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.entities.UserRating;

@Repository
public class UserRatingDAOImp implements UserRatingDAO {

	private EntityManager entityManager;

	@Autowired
	public UserRatingDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(UserRating userRating) {
		Session session = entityManager.unwrap(Session.class);
		session.save(userRating);

	}

	@Override
	public UserRating getUserRatingByCreatorAndTarget(UserProfile creator, UserProfile target) {
		Session session = entityManager.unwrap(Session.class);
		String query = "from UserRating u where u.creator = :creator and u.target = :target";

		UserRating result = (UserRating) session.createQuery(query).setParameter("creator", creator)
				.setParameter("target", target).getResultStream().findFirst().orElse(null);
		return result;
	}

	@Override
	public long getUserLikeCount(int userId) {
		Session session = entityManager.unwrap(Session.class);
		String query = "select count(*) from UserRating u join u.target t where t.userId = :id and u.userRatingPoint = 1";
		Query countQ =  session.createQuery(query).setParameter("id", userId);
		long count  = (long) countQ.uniqueResult() ;
		return count;
	}

	@Override
	public long getUserDisLikeCount(int userId) {
		Session session = entityManager.unwrap(Session.class);
		String query = "select count(*) from UserRating u join u.target t where t.userId = :id and u.userRatingPoint = 0";
		Query countQ =  session.createQuery(query).setParameter("id", userId);
		long count  = (long) countQ.uniqueResult() ;
		return count;
	}

}
