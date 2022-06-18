package com.uit.chophen.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.uit.chophen.fcmdomain.FCMTokenStoreObj;

public interface PushNotificationService {

	 public void sendMessageToToken(String fcmToken) throws InterruptedException, ExecutionException, FirebaseMessagingException;
	 public void registerDevice(String fcmToken, int userId);
	 public List<FCMTokenStoreObj> getFCMTokensByUserId(int userId) throws InterruptedException, ExecutionException;
	 
	 public void sendLikeNotificationsToUser(int userId) throws InterruptedException, ExecutionException, FirebaseMessagingException;
	 public void sendDisLikeNotificationsToUser(int userId) throws InterruptedException, ExecutionException, FirebaseMessagingException;
	 public void sendNewMessageNotificationToUser(int userId) throws InterruptedException, ExecutionException, FirebaseMessagingException;


}
