package com.uit.chophen.httpdomains.response;
import java.util.List;

import com.uit.chophen.entities.UserNotification;
public class GetNotificationsResponseBody {

	private List<UserNotification> notificationList;

	public List<UserNotification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<UserNotification> notificationList) {
		this.notificationList = notificationList;
	}
}
