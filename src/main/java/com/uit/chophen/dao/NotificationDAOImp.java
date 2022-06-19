package com.uit.chophen.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
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

	
}
