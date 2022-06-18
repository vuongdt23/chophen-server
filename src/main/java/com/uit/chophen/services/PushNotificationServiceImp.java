package com.uit.chophen.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.uit.chophen.dao.UserProfileDAO;
import com.uit.chophen.entities.UserProfile;
import com.uit.chophen.fcmdomain.FCMTokenStoreObj;

@Service
public class PushNotificationServiceImp implements PushNotificationService {

	private UserProfileDAO userProfileDAO;
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sendMessageToToken(String fcmToken)
			throws InterruptedException, ExecutionException, FirebaseMessagingException {

		FirebaseMessaging.getInstance()
				.sendMulticast(MulticastMessage.builder().addToken(fcmToken)
						.setAndroidConfig(AndroidConfig.builder()
								.setNotification(AndroidNotification.builder().setTitle("Title").setBody("Message")
										.setColor("#f45342").setIcon("push_icon").build())
								.build())
						.putData("hello", "world").build(), false);
		// Response is a message ID string.

	}

	@Autowired
	public PushNotificationServiceImp(UserProfileDAO userProfileDAO) {
		this.userProfileDAO = userProfileDAO;
	}

	@Override
	public void registerDevice(String fcmToken, int userId) {
		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference fcmTokens = firestore.collection("FCM_TOKENS");
		Map<String, Object> newEntry = new HashMap<>();
		newEntry.put("fcmToken", fcmToken);
		newEntry.put("userId", userId);

		ApiFuture<WriteResult> future = fcmTokens.document(fcmToken).set(newEntry);

		LOGGER.info("new device registered: " + fcmToken + " by user: " + userId);
		try {
			LOGGER.info("Updated time: " + future.get().getUpdateTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<FCMTokenStoreObj> getFCMTokensByUserId(int userId) throws InterruptedException, ExecutionException {
		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference fcmTokens = firestore.collection("FCM_TOKENS");
		ApiFuture<QuerySnapshot> future = fcmTokens.whereEqualTo("userId", userId).get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();

		List<FCMTokenStoreObj> userFCMTokens = new ArrayList<FCMTokenStoreObj>();
		for (DocumentSnapshot document : documents) {
			userFCMTokens.add(document.toObject(FCMTokenStoreObj.class));

		}

		return userFCMTokens;
	}

	private AndroidNotification getAndroidLikeNotification(UserProfile creatorUser) {
		return AndroidNotification.builder().setTitle("Hồ sơ của bạn được thích")
				.setBody(creatorUser.getUserFullName() + " đã thích hồ sơ của bạn").setIcon("thumb_up").build();
	}

	private AndroidNotification getAndroidDisLikeNotification(UserProfile creatorUser) {
		return AndroidNotification.builder().setTitle("Hồ sơ của bạn được thích").setIcon("thumb_down")
				.setBody(creatorUser.getUserFullName() + " không thích hồ sơ của bạn").build();
	}
	
	private AndroidNotification getAndroidNewMessageNotification(UserProfile messageSender) {
		return AndroidNotification.builder().setTitle("Bạn có tin nhắn mới")
				.setBody(messageSender.getUserFullName() + " đã gửi cho bạn một tin nhắn mới").setIcon("mark_chat_unread").build();
	}

	@Override
	public void sendLikeNotificationsToUser(int userId) throws InterruptedException, ExecutionException, FirebaseMessagingException {
		UserProfile creatorUser = userProfileDAO.findUserProfileById(userId);
		List<FCMTokenStoreObj> userTokens = getFCMTokensByUserId(userId);
		List<String> tokens = new ArrayList<String>();
		for (int i = 0; i < userTokens.size(); i++) {
			tokens.add(userTokens.get(i).getFcmToken());
		}

		MulticastMessage message = MulticastMessage.builder().addAllTokens(tokens)
				.setAndroidConfig(
						AndroidConfig.builder().setNotification(getAndroidLikeNotification(creatorUser)).build())
				.build();
		
		FirebaseMessaging.getInstance().sendMulticast(message);
	}

	@Override
	public void sendDisLikeNotificationsToUser(int userId)
			throws InterruptedException, ExecutionException, FirebaseMessagingException {
		UserProfile creatorUser = userProfileDAO.findUserProfileById(userId);
		List<FCMTokenStoreObj> userTokens = getFCMTokensByUserId(userId);
		List<String> tokens = new ArrayList<String>();
		for (int i = 0; i < userTokens.size(); i++) {
			tokens.add(userTokens.get(i).getFcmToken());
		}

		MulticastMessage message = MulticastMessage.builder().addAllTokens(tokens)
				.setAndroidConfig(
						AndroidConfig.builder().setNotification(getAndroidDisLikeNotification(creatorUser)).build())
				.build();
		
		FirebaseMessaging.getInstance().sendMulticast(message);
		
	}

	@Override
	public void sendNewMessageNotificationToUser(int userId)
			throws InterruptedException, ExecutionException, FirebaseMessagingException {
		UserProfile messageSender = userProfileDAO.findUserProfileById(userId);
		List<FCMTokenStoreObj> userTokens = getFCMTokensByUserId(userId);
		List<String> tokens = new ArrayList<String>();
		for (int i = 0; i < userTokens.size(); i++) {
			tokens.add(userTokens.get(i).getFcmToken());
		}

		MulticastMessage message = MulticastMessage.builder().addAllTokens(tokens)
				.setAndroidConfig(
						AndroidConfig.builder().setNotification(getAndroidNewMessageNotification(messageSender)).build())
				.build();
		
		FirebaseMessaging.getInstance().sendMulticast(message);
		
	}
}
