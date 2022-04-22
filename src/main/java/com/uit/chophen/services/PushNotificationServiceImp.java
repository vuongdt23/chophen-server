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
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.uit.chophen.fcmdomain.FCMTokenStoreObj;

@Service
public class PushNotificationServiceImp implements PushNotificationService {

	

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sendMessageToToken(String fcmToken) throws InterruptedException, ExecutionException {

		
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

}
