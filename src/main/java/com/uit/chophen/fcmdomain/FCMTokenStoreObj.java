package com.uit.chophen.fcmdomain;

public class FCMTokenStoreObj {

	private int userId;
	private String fcmToken;

	@Override
	public String toString() {
		return "FCMTokenStoreObj [userId=" + userId + ", fcmToken=" + fcmToken + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
}
