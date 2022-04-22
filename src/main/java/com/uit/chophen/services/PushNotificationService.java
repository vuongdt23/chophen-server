package com.uit.chophen.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.uit.chophen.fcmdomain.FCMTokenStoreObj;

public interface PushNotificationService {

	 public void sendMessageToToken(String fcmToken) throws InterruptedException, ExecutionException;
	 public void registerDevice(String fcmToken, int userId);
	 public List<FCMTokenStoreObj> getFCMTokensByUserId(int userId) throws InterruptedException, ExecutionException;
}
