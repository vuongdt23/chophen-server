package com.uit.chophen.services;

import com.uit.chophen.entities.UserNotification;

public interface NotificationService {
	
	public UserNotification createLikeNotification(int receiveUserId, int sendUserId);
	public UserNotification createDisLikeNotification(int receiveUserId, int sendUserId);
	public UserNotification createNewMessageNotification(int receiveUserId, int sendUserId);


}
