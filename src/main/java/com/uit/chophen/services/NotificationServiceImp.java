package com.uit.chophen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uit.chophen.dao.NotificationDAO;
import com.uit.chophen.entities.UserNotification;

@Service
public class NotificationServiceImp implements NotificationService {

	private NotificationDAO notificationDAO;
	
	@Autowired
	public NotificationServiceImp(NotificationDAO notificationDAO) {
		super();
		this.notificationDAO = notificationDAO;
	}

	@Override
	public UserNotification createLikeNotification(int receiveUserId, int sendUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserNotification createDisLikeNotification(int receiveUserId, int sendUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserNotification createNewMessageNotification(int receiveUserId, int sendUserId) {
		// TODO Auto-generated method stub
		return null;
	}

}
