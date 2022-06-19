package com.uit.chophen.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uit.chophen.entities.UserNotification;
import com.uit.chophen.entities.UserProfile;

@Repository
public class NotificationDAOImp implements NotificationDAO {
	
	private EntityManager entityManager;

	@Autowired
	public NotificationDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UserNotification insertNewMessageNotification(UserProfile receiveUser, UserProfile sendUser) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		UserNotification newNotification = new UserNotification();
		newNotification.setUserNotificationImage(sendUser.getUserPic());
		newNotification.setUserNotificationRead(false);
		newNotification.setUserNotificationTitle("Bạn có tin nhắn mới!");
		newNotification.setUserNotificationBody(sendUser.getUserFullName()+ " đã gửi cho bạn một tin nhắn mới");
		newNotification.setUserProfile(receiveUser);
		session.save(newNotification);
		return newNotification;
	}

	@Override
	public UserNotification insertLikeNotification(UserProfile receiveUser, UserProfile sendUser) {
		Session session = entityManager.unwrap(Session.class);
		UserNotification newNotification = new UserNotification();
		newNotification.setUserNotificationImage(sendUser.getUserPic());
		newNotification.setUserNotificationRead(false);
		newNotification.setUserNotificationTitle("Ai đó thích hồ sơ của bạn");
		newNotification.setUserNotificationBody(sendUser.getUserFullName()+ " đã thích hồ sơ của bạn");
		newNotification.setUserProfile(receiveUser);
		session.save(newNotification);
		return newNotification;
	}

	@Override
	public UserNotification insertDisLikeNotification(UserProfile receiveUser, UserProfile sendUser) {
		Session session = entityManager.unwrap(Session.class);
		UserNotification newNotification = new UserNotification();
		newNotification.setUserNotificationImage(sendUser.getUserPic());
		newNotification.setUserNotificationRead(false);
		newNotification.setUserNotificationTitle("Ai đó không thích hồ sơ của bạn");
		newNotification.setUserNotificationBody(sendUser.getUserFullName()+ " không thích hồ sơ của bạn");
		newNotification.setUserProfile(receiveUser);
		session.save(newNotification);
		return newNotification;
	}

	@Override
	public List<UserNotification> getUserNotificationByUserId(int userId) {
		Session session = entityManager.unwrap(Session.class);		
		String selectQ = "select un from UserNotification un join un.userProfile up where up.userId =:uId";
		Query selectQuery = session.createQuery(selectQ, UserNotification.class);
		selectQuery.setParameter("uId", userId);
		List<UserNotification> notificationList = selectQuery.getResultList();
		return notificationList;
	}

	@Override
	public UserNotification getNotificationById(int notificationId) {
		Session session = entityManager.unwrap(Session.class);		
		UserNotification result = session.get(UserNotification.class, notificationId);
		return result;
	}

	@Override
	public UserNotification saveNotification(UserNotification userNotification) {
		Session session = entityManager.unwrap(Session.class);		
		session.saveOrUpdate(userNotification);
		return userNotification;
	}

	
}
