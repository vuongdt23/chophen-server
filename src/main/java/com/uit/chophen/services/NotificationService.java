package com.uit.chophen.services;

import java.util.List;

import com.uit.chophen.entities.UserNotification;

public interface NotificationService {
	
	public UserNotification createLikeNotification(int receiveUserId, int sendUserId);
	public UserNotification createDisLikeNotification(int receiveUserId, int sendUserId);
	public UserNotification createNewMessageNotification(int receiveUserId, int sendUserId);
    public List<UserNotification> getUserNotifications(int userId);
    public boolean checkOwnership(int userId, int notificationId);
    public UserNotification setNotificationAsRead(int notificationId);
    public boolean checkIsRead(int notificationId);
    public void setAllUserNotificationAsRead(int userId);
    public boolean checkHasUnreadNotifications(int userId);
}
