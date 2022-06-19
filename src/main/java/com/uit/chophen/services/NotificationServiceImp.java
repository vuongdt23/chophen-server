package com.uit.chophen.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uit.chophen.dao.NotificationDAO;
import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.UserNotification;
import com.uit.chophen.entities.UserProfile;

@Service
public class NotificationServiceImp implements NotificationService {

	private NotificationDAO notificationDAO;
	private UserProfileDAO userProfileDAO;
	
	@Autowired
	public NotificationServiceImp(NotificationDAO notificationDAO, UserProfileDAO userProfileDAO) {
		this.notificationDAO = notificationDAO;
		this.userProfileDAO = userProfileDAO;
	}

	@Override
	@Transactional
	public UserNotification createLikeNotification(int receiveUserId, int sendUserId) {
		UserProfile receiveUser = userProfileDAO.findUserProfileById(receiveUserId);
		UserProfile sendUser = userProfileDAO.findUserProfileById(sendUserId);

		return notificationDAO.insertLikeNotification(receiveUser, sendUser);
	}

	
	@Override
	public UserNotification createDisLikeNotification(int receiveUserId, int sendUserId) {
		UserProfile receiveUser = userProfileDAO.findUserProfileById(receiveUserId);
		UserProfile sendUser = userProfileDAO.findUserProfileById(sendUserId);

		return notificationDAO.insertDisLikeNotification(receiveUser, sendUser);
	}

	@Override
	public UserNotification createNewMessageNotification(int receiveUserId, int sendUserId) {
		UserProfile receiveUser = userProfileDAO.findUserProfileById(receiveUserId);
		UserProfile sendUser = userProfileDAO.findUserProfileById(sendUserId);

		return notificationDAO.insertNewMessageNotification(receiveUser, sendUser);
	}

	@Override
	public List<UserNotification> getUserNotifications(int userId) {
		// TODO Auto-generated method stub
		return notificationDAO.getUserNotificationByUserId(userId);
	}

	@Override
	public boolean checkOwnership(int userId, int notificationId) {
		UserNotification userNotification = notificationDAO.getNotificationById(notificationId);
		if(userId != userNotification.getUserProfile().getUserId()) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public UserNotification setNotificationAsRead(int notificationId) {
		UserNotification userNotification = notificationDAO.getNotificationById(notificationId);
		userNotification.setUserNotificationRead(true);
		notificationDAO.saveNotification(userNotification); 
		return userNotification;
	}

	@Override
	public boolean checkIsRead(int notificationId) {
		UserNotification userNotification = notificationDAO.getNotificationById(notificationId);		
		return userNotification.isUserNotificationRead();
	}

}
