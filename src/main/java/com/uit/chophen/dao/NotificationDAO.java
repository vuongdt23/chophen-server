package com.uit.chophen.dao;

import com.uit.chophen.entities.UserNotification;
import com.uit.chophen.entities.UserProfile;

public interface NotificationDAO {

	public UserNotification insertNewMessageNotification(UserProfile receiveUser, UserProfile sendUser);
	public UserNotification insertLikeNotification(UserProfile receiveUser, UserProfile sendUser);
	public UserNotification insertDisLikeNotification(UserProfile receiveUser, UserProfile sendUser);

}
